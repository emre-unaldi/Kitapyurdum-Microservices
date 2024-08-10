package patika.invoiceservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.invoiceservice.entity.Invoice;
import patika.invoiceservice.entity.dto.request.InvoiceSaveRequest;
import patika.invoiceservice.entity.dto.response.InvoiceResponse;
import patika.invoiceservice.entity.dto.response.OrderResponse;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceConverter {
    public static Invoice toInvoice(InvoiceSaveRequest request) {
        return Invoice.builder()
                .createdDatetime(LocalDateTime.now())
                .orderId(request.getOrderId())
                .build();
    }

    public static InvoiceResponse toInvoiceResponse(Invoice invoice, OrderResponse orderResponse) {
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .createdDatetime(invoice.getCreatedDatetime())
                .order(orderResponse)
                .build();
    }
}

