package patika.invoiceservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
public class BookResponse {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private String description;
    private Integer publisherId;
    private Integer authorId;
}
