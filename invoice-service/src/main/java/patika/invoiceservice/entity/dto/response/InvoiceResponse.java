package patika.invoiceservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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
public class InvoiceResponse {
    private Integer id;
    private Double amount;
    private LocalDateTime createdDatetime;
    private OrderResponse order;
}
