package patika.publisherservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
public class PublisherResponse {
    private Integer id;
    private String name;
    private LocalDate createdDate;
    private List<PublisherBookResponse> books;
    private List<PublisherMagazineResponse> magazines;
}
