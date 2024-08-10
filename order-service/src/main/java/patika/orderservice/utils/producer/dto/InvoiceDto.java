package patika.orderservice.utils.producer.dto;

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
public class InvoiceDto {
    private Integer id;
    private Double amount;
    private LocalDateTime createdDatetime;
}
