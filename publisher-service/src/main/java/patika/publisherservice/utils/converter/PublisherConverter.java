package patika.publisherservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.publisherservice.entity.Publisher;
import patika.publisherservice.entity.dto.request.PublisherSaveRequest;
import patika.publisherservice.entity.dto.response.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherConverter {

    public static Publisher toPublisher(PublisherSaveRequest request) {
        return Publisher.builder()
                .name(request.getName())
                .createdDate(LocalDate.now())
                .build();
    }

    public static PublisherResponse toPublisherResponse(Publisher publisher, List<BookResponse> books, List<MagazineResponse> magazines) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .createdDate(publisher.getCreatedDate())
                .books(PublisherConverter.toListPublisherBookResponse(books))
                .magazines(PublisherConverter.toListPublisherMagazineResponse(magazines))
                .build();
    }

    public static PublisherProductResponse toPublisherProductResponse(List<BookResponse> bookResponses, List<MagazineResponse> magazineResponses) {
        return PublisherProductResponse.builder()
                .books(PublisherConverter.toListPublisherBookResponse(bookResponses))
                .magazines(PublisherConverter.toListPublisherMagazineResponse(magazineResponses))
                .build();
    }

    public static PublisherBookResponse toPublisherBookResponse(BookResponse bookResponse) {
        return PublisherBookResponse.builder()
                .id(bookResponse.getId())
                .name(bookResponse.getName())
                .amount(bookResponse.getAmount())
                .description(bookResponse.getDescription())
                .authorId(bookResponse.getAuthorId())
                .build();
    }

    public static List<PublisherBookResponse> toListPublisherBookResponse(List<BookResponse> bookResponses) {
        return bookResponses.stream()
                .map(PublisherConverter::toPublisherBookResponse)
                .toList();
    }

    public static PublisherMagazineResponse toPublisherMagazineResponse(MagazineResponse magazineResponse) {
        return PublisherMagazineResponse.builder()
                .id(magazineResponse.getId())
                .name(magazineResponse.getName())
                .amount(magazineResponse.getAmount())
                .description(magazineResponse.getDescription())
                .publishDate(magazineResponse.getPublishDate())
                .authorIds(magazineResponse.getAuthorIds())
                .build();
    }

    public static List<PublisherMagazineResponse> toListPublisherMagazineResponse(List<MagazineResponse> magazineResponses) {
        return magazineResponses.stream()
                .map(PublisherConverter::toPublisherMagazineResponse)
                .toList();
    }
}
