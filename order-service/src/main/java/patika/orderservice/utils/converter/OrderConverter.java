package patika.orderservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.response.*;
import patika.orderservice.entity.enums.OrderStatus;
import patika.orderservice.utils.producer.dto.InvoiceDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {
    public static Order toOrder(OrderSaveRequest request) {
        return Order.builder()
                .customerId(request.getCustomerId())
                .invoiceId(request.getInvoiceId())
                .createdDate(LocalDateTime.now())
                .orderStatus(OrderStatus.INITIAL)
                .bookIds(request.getBookIds())
                .magazineIds(request.getMagazineIds())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order, List<BookResponse> books, List<MagazineResponse> magazines) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .invoiceId(order.getInvoiceId())
                .orderCode(order.getOrderCode())
                .orderStatus(order.getOrderStatus())
                .createdDate(order.getCreatedDate())
                .books(books)
                .magazines(magazines)
                .build();
    }

    public static BookProductResponse toBookProductResponse(BookResponse bookResponse) {
        return BookProductResponse.builder()
                .name(bookResponse.getName())
                .description(bookResponse.getDescription())
                .build();
    }

    public static List<BookProductResponse> toListBookProductResponse(List<BookResponse> books) {
        return books.stream()
                .map(OrderConverter::toBookProductResponse)
                .toList();
    }


    public static MagazineProductResponse toMagazineProductResponse(MagazineResponse magazineResponse) {
        return MagazineProductResponse.builder()
                .name(magazineResponse.getName())
                .description(magazineResponse.getDescription())
                .build();
    }

    public static List<MagazineProductResponse> toListMagazineProductResponse(List<MagazineResponse> magazines) {
        return magazines.stream()
                .map(OrderConverter::toMagazineProductResponse)
                .toList();
    }

    public static InvoiceDto toInvoiceDto(InvoiceResponse invoiceResponse) {
        return InvoiceDto.builder()
                .id(invoiceResponse.getId())
                .amount(invoiceResponse.getAmount())
                .createdDatetime(invoiceResponse.getCreatedDatetime())
                .build();
    }
}
