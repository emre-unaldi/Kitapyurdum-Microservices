package patika.invoiceservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.invoiceservice.entity.Invoice;
import patika.invoiceservice.entity.dto.request.InvoiceSaveRequest;
import patika.invoiceservice.entity.dto.response.InvoiceResponse;
import patika.invoiceservice.entity.dto.response.OrderResponse;
import patika.invoiceservice.entity.dto.response.BookResponse;
import patika.invoiceservice.entity.dto.response.MagazineResponse;
import patika.invoiceservice.repository.InvoiceRepository;
import patika.invoiceservice.service.InvoiceService;
import patika.invoiceservice.utils.client.service.OrderService;
import patika.invoiceservice.utils.converter.InvoiceConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderService orderService;

    /**
     * Constructs a new instance of {@code InvoiceServiceImpl} using the provided {@link InvoiceRepository} and {@link OrderService}.
     *
     * @param invoiceRepository An instance of {@link InvoiceRepository} to interact with the database for invoice-related operations
     * @param orderService      An instance of {@link OrderService} to manage order-related operations, which are necessary for creating invoices
     */
    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, OrderService orderService) {
        this.invoiceRepository = invoiceRepository;
        this.orderService = orderService;
    }

    /**
     * Saves an invoice record and returns the saved invoice along with a successful operation response.
     *
     * @param request A {@link InvoiceSaveRequest} object containing the information for the new invoice
     * @return A {@link InvoiceResponse} object representing the saved invoice and a successful operation response
     */
    @Override
    public InvoiceResponse save(InvoiceSaveRequest request) {
        OrderResponse orderResponse = orderService.findById(request.getOrderId());

        Invoice invoice = InvoiceConverter.toInvoice(request);
        invoice.setAmount(calculateTotalAmount(orderResponse));
        invoiceRepository.save(invoice);

        return InvoiceConverter.toInvoiceResponse(invoice, orderResponse);
    }

    /**
     * Retrieves all invoices and returns them as a list of {@link InvoiceResponse} objects.
     *
     * @return A list of {@link InvoiceResponse} objects representing all invoices
     */
    @Override
    public List<InvoiceResponse> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return invoices.stream()
                .map(invoice -> {
                            OrderResponse orderResponse = orderService.findById(invoice.getOrderId());
                            return InvoiceConverter.toInvoiceResponse(invoice, orderResponse);
                        }
                )
                .toList();
    }

    /**
     * Finds an invoice by its ID and returns it as a {@link InvoiceResponse} object.
     *
     * @param invoiceId The ID of the invoice to find
     * @return The found invoice as a {@link InvoiceResponse} object, or null if not found
     */
    @Override
    public InvoiceResponse findById(Integer invoiceId) {
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoiceId);

        if (foundInvoice.isEmpty()) return null;

        Invoice invoice = foundInvoice.get();
        OrderResponse orderResponse = orderService.findById(invoice.getOrderId());

        return InvoiceConverter.toInvoiceResponse(invoice, orderResponse);
    }

    /**
     * Calculates the total amount for the provided order response.
     *
     * @param orderResponse An {@link OrderResponse} object representing the order.
     * @return The total amount as a {@link Double}.
     */
    private Double calculateTotalAmount(OrderResponse orderResponse) {
        BigDecimal totalBookAmount = orderResponse.getBooks().stream()
                .map(BookResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMagazineAmount = orderResponse.getMagazines().stream()
                .map(MagazineResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAmount = totalBookAmount.add(totalMagazineAmount);

        return totalAmount.doubleValue();
    }
}

