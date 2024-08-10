package patika.publisherservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class PublisherMagazineResponse {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private String description;
    private LocalDate publishDate;
    private List<Integer> authorIds;
}
