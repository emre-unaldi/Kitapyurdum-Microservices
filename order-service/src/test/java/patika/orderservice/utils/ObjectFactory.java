package patika.orderservice.utils;

import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.response.*;
import patika.orderservice.entity.enums.OrderStatus;
import patika.orderservice.utils.producer.dto.InvoiceDto;
import patika.orderservice.utils.producer.dto.ProductDto;
import org.instancio.Instancio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.instancio.Select.field;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
public class ObjectFactory {
    private static ObjectFactory instance;
    private OrderSaveRequest orderSaveRequest;
    private Order order;
    private BookResponse bookResponse;
    private MagazineResponse magazineResponse;
    private OrderResponse orderResponse;
    private InvoiceResponse invoiceResponse;
    private BookProductResponse bookProductResponse;
    private MagazineProductResponse magazineProductResponse;
    private ProductDto productDto;
    private InvoiceDto invoiceDto;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public OrderSaveRequest getOrderSaveRequest() {
        if (orderSaveRequest == null) {
            orderSaveRequest = Instancio.of(OrderSaveRequest.class)
                    .set(field("customerId"), 1)
                    .set(field("invoiceId"), 1)
                    .set(field("bookIds"), Arrays.asList(1,2,3))
                    .set(field("magazineIds"), Arrays.asList(1,2,3))
                    .create();
        }

        return orderSaveRequest;
    }

    public Order getOrder() {
        if (order == null) {
            order = Instancio.of(Order.class)
                    .set(field("id"), 1)
                    .set(field("customerId"), 1)
                    .set(field("invoiceId"), 1)
                    .set(field("orderCode"), "ASD2428678BD326")
                    .set(field("orderStatus"), OrderStatus.INITIAL)
                    .set(field("createdDate"), LocalDateTime.now())
                    .set(field("bookIds"), Arrays.asList(1,2,3))
                    .set(field("magazineIds"), Arrays.asList(1,2,3))
                    .create();
        }

        return order;
    }

    public BookResponse getBookResponse() {
        if (bookResponse == null) {
            bookResponse = Instancio.of(BookResponse.class)
                    .set(field("id"), 1)
                    .set(field("name"), "Book 1")
                    .set(field("amount"), BigDecimal.valueOf(1250.0))
                    .set(field("description"), "Book Description 1")
                    .set(field("publisherId"), 1)
                    .set(field("authorId"), 1)
                    .create();
        }

        return bookResponse;
    }

    public MagazineResponse getMagazineResponse() {
        if (magazineResponse == null) {
            magazineResponse = Instancio.of(MagazineResponse.class)
                    .set(field("id"), 1)
                    .set(field("name"), "Magazine 1")
                    .set(field("amount"), BigDecimal.valueOf(1250.0))
                    .set(field("description"), "Magazine Description 1")
                    .set(field("publishDate"),LocalDate.of(2024, 6, 23))
                    .set(field("publisherId"), 1)
                    .set(field("authorIds"), Arrays.asList(1,2,3))
                    .create();
        }

        return magazineResponse;
    }

    public OrderResponse getOrderResponse() {
        if (orderResponse == null) {
            orderResponse = Instancio.of(OrderResponse.class)
                    .set(field("id"), 1)
                    .set(field("customerId"), 1)
                    .set(field("invoiceId"), 1)
                    .set(field("orderCode"), "ASD2428678BD326")
                    .set(field("orderStatus"), OrderStatus.INITIAL)
                    .set(field("createdDate"), LocalDateTime.now())
                    .set(field("books"), List.of(getBookResponse()))
                    .set(field("magazines"), List.of(getMagazineResponse()))
                    .create();
        }

        return orderResponse;
    }

    public InvoiceResponse getInvoiceResponse() {
        if (invoiceResponse == null) {
            invoiceResponse = Instancio.of(InvoiceResponse.class)
                    .set(field("id"), 1)
                    .set(field("amount"), 1250.0)
                    .set(field("createdDatetime"), LocalDateTime.now())
                    .set(field("order"), getOrderResponse())
                    .create();
        }

        return invoiceResponse;
    }

    public BookProductResponse getBookProductResponse() {
        if (bookProductResponse == null) {
            bookProductResponse = Instancio.of(BookProductResponse.class)
                    .set(field("name"), "Book 1")
                    .set(field("description"), "Book Description 1")
                    .create();
        }

        return bookProductResponse;
    }

    public MagazineProductResponse getMagazineProductResponse() {
        if (magazineProductResponse == null) {
            magazineProductResponse = Instancio.of(MagazineProductResponse.class)
                    .set(field("name"), "Book 1")
                    .set(field("description"), "Book Description 1")
                    .create();
        }

        return magazineProductResponse;
    }

    public ProductDto getProductDto() {
        if (productDto == null) {
            productDto = Instancio.of(ProductDto.class)
                    .set(field("books"), List.of(getBookProductResponse()))
                    .set(field("magazines"), List.of(getMagazineProductResponse()))
                    .set(field("totalAmount"), BigDecimal.valueOf(1250.0))
                    .create();
        }

        return productDto;
    }

    public InvoiceDto getInvoiceDto() {
        if(invoiceDto == null) {
            invoiceDto = Instancio.of(InvoiceDto.class)
                    .set(field("id"), 1)
                    .set(field("amount"), 1250.0)
                    .set(field("createdDatetime"), LocalDateTime.now())
                    .create();
        }

        return null;
    }
}
