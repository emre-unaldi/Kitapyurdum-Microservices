package patika.authorservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@AllArgsConstructor
@Builder
public class AuthorMagazineResponse {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private String description;
    private LocalDate publishDate;
    private Integer publisherId;
}
