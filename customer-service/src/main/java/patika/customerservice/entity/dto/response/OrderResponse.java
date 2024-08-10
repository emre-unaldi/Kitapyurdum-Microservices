package patika.customerservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import patika.customerservice.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@Builder
@AllArgsConstructor
public class OrderResponse {
    private Integer id;
    private Integer customerId;
    private Integer invoiceId;
    private String orderCode;
    private OrderStatus orderStatus;
    private LocalDateTime createdDate;
    private List<BookResponse> books;
    private List<MagazineResponse> magazines;
}
