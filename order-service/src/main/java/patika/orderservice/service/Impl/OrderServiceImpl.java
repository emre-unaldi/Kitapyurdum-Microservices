package patika.orderservice.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.request.OrderSearchRequest;
import patika.orderservice.entity.dto.response.*;
import patika.orderservice.repository.specification.OrderSpecification;
import patika.orderservice.utils.client.service.InvoiceService;
import patika.orderservice.utils.producer.dto.InvoiceDto;
import patika.orderservice.utils.producer.dto.ProductDto;
import patika.orderservice.entity.enums.OrderStatus;
import patika.orderservice.repository.OrderRepository;
import patika.orderservice.service.OrderService;
import patika.orderservice.utils.client.service.ProductService;
import patika.orderservice.utils.converter.OrderConverter;
import patika.orderservice.utils.producer.NotificationProducer;
import patika.orderservice.utils.producer.dto.NotificationDto;
import patika.orderservice.utils.producer.dto.enums.NotificationStatus;
import patika.orderservice.utils.producer.dto.enums.NotificationType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final NotificationProducer notificationProducer;

    /**
     * Constructs a new instance of {@code OrderServiceImpl} using the provided dependencies.
     *
     * @param orderRepository An instance of {@link OrderRepository} to interact with the database for order-related operations
     * @param productService An instance of {@link ProductService} to manage products-related operations
     * @param invoiceService An instance of {@link InvoiceService} to handle invoice generation
     * @param notificationProducer An instance of {@link NotificationProducer} to send notifications
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, InvoiceService invoiceService, NotificationProducer notificationProducer) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.invoiceService = invoiceService;
        this.notificationProducer = notificationProducer;
    }

    /**
     * Saves an order record and sets its initial status. Returns the saved order along with a success message.
     *
     * @param request An {@link OrderSaveRequest} object containing the order's details
     * @return An {@link OrderResponse} object representing the saved order
     */
    @Override
    public OrderResponse save(OrderSaveRequest request) {
        Order order = OrderConverter.toOrder(request);
        order.setOrderCode(generateUniqueOrderCode(order.getOrderStatus()));

        orderRepository.save(order);

        List<BookResponse> books = productService.filterByBookIdList(order.getBookIds());
        List<MagazineResponse> magazines = productService.filterByMagazineIdList(order.getMagazineIds());
        OrderResponse orderResponse = OrderConverter.toOrderResponse(order, books, magazines);

        BigDecimal totalAmount = calculateTotalAmount(orderResponse);
        ProductDto productDto = prepareProductDto(books, magazines, totalAmount);
        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.SMS, NotificationStatus.SUCCESS, productDto));

        InvoiceDto invoiceDto = OrderConverter.toInvoiceDto(invoiceService.save(order.getId()));
        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.MAIL, NotificationStatus.SUCCESS, invoiceDto));

        return orderResponse;
    }

    /**
     * Retrieves all orders and converts them to a list of {@link OrderResponse} objects.
     *
     * @return A list of {@link OrderResponse} objects representing all orders
     */
    @Override
    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::prepareOrderResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> search(OrderSearchRequest request) {

        Specification<Order> orderSpecification = OrderSpecification.initOrderSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        Page<Order> orders = orderRepository.findAll(orderSpecification, pageRequest);

        return prepareOrderResponse(orders.stream().toList());
    }


    /**
     * Finds an order by its ID and returns it as an {@link OrderResponse} object.
     *
     * @param orderId The ID of the order to find
     * @return The found order as an {@link OrderResponse} object, or null if not found
     */
    @Override
    public OrderResponse findById(Integer orderId) {
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        if (foundOrder.isEmpty()) return null;
        Order order = foundOrder.get();

        return prepareOrderResponse(order);
    }

    /**
     * Retrieves all orders placed by a specific customer and converts them to a list of {@link OrderResponse} objects.
     *
     * @param customerId The ID of the customer whose orders are to be retrieved
     * @return A list of {@link OrderResponse} objects representing all orders placed by the specified customer
     */
    @Override
    public List<OrderResponse> filterByCustomerId(Integer customerId) {
        List<Order> orders = orderRepository.filterByCustomerId(customerId);

        return orders.stream()
                .map(this::prepareOrderResponse)
                .toList();
    }

    /**
     * Generates a unique order code for an order based on the order's status and the current timestamp.
     *
     * @param orderStatus The status of the order, used to generate a part of the unique order code.
     * @return A unique order code composed of a prefix, a randomly selected substring from the order status,
     *         and the current timestamp in milliseconds.
     */
    public String generateUniqueOrderCode(OrderStatus orderStatus) {
        Random random = new Random();
        StringBuilder statusShortCode = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(orderStatus.name().length());
            statusShortCode.append(orderStatus.name().charAt(index));
        }

        long millis = System.currentTimeMillis();
        String orderCodePrefix = "ORD";

        return orderCodePrefix + "-" + statusShortCode + "-" + millis;
    }

    /**
     * Calculates the total amount for the provided order response.
     *
     * @param orderResponse An {@link OrderResponse} object representing the order.
     * @return The total amount as a {@link Double}.
     */
    public BigDecimal calculateTotalAmount(OrderResponse orderResponse) {
        BigDecimal totalBookAmount = orderResponse.getBooks().stream()
                .map(BookResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMagazineAmount = orderResponse.getMagazines().stream()
                .map(MagazineResponse::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalBookAmount.add(totalMagazineAmount);
    }

    /**
     * Prepares an {@link OrderResponse} object from an {@link Order} entity.
     *
     * @param order The {@link Order} entity to convert
     * @return The prepared {@link OrderResponse} object
     */
    public OrderResponse prepareOrderResponse(Order order) {
        List<BookResponse> books = productService.filterByBookIdList(order.getBookIds());
        List<MagazineResponse> magazines = productService.filterByMagazineIdList(order.getMagazineIds());

        return OrderConverter.toOrderResponse(order, books, magazines);
    }

    /**
     * Prepares a {@link ProductDto} object from lists of {@link BookResponse} and {@link MagazineResponse}, and a total amount.
     *
     * @param books A list of {@link BookResponse} objects
     * @param magazines A list of {@link MagazineResponse} objects
     * @param totalAmount The total amount as a {@link BigDecimal}
     * @return The prepared {@link ProductDto} object
     */
    public ProductDto prepareProductDto(List<BookResponse> books, List<MagazineResponse> magazines, BigDecimal totalAmount) {
        return ProductDto.builder()
                .books(OrderConverter.toListBookProductResponse(books))
                .magazines(OrderConverter.toListMagazineProductResponse(magazines))
                .totalAmount(totalAmount)
                .build();
    }

    /**
     * Prepares a {@link NotificationDto} object for sending SMS notifications.
     *
     * @param notificationType The type of notification
     * @param productDto The {@link ProductDto} object containing product details
     * @return The prepared {@link NotificationDto} object
     */
    public NotificationDto prepareNotificationDto(NotificationType notificationType, NotificationStatus notificationStatus, ProductDto productDto) {
        return NotificationDto.builder()
                .notificationType(notificationType)
                .notificationStatus(notificationStatus)
                .productDto(productDto)
                .customerDto(null)
                .invoiceDto(null)
                .build();
    }

    /**
     * Prepares a {@link NotificationDto} object for sending email notifications.
     *
     * @param notificationType The type of notification
     * @param invoiceDto The {@link InvoiceDto} object containing invoice details
     * @return The prepared {@link NotificationDto} object
     */
    public NotificationDto prepareNotificationDto(NotificationType notificationType, NotificationStatus notificationStatus, InvoiceDto invoiceDto) {
        return NotificationDto.builder()
                .notificationType(notificationType)
                .notificationStatus(notificationStatus)
                .invoiceDto(invoiceDto)
                .customerDto(null)
                .productDto(null)
                .build();
    }
}
