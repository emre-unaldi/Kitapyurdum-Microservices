package patika.publisherservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
public class PublisherProductResponse {
    List<PublisherBookResponse> books;
    List<PublisherMagazineResponse> magazines;
}
