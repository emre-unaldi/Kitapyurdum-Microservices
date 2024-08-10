package patika.authorservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.authorservice.entity.Author;
import patika.authorservice.entity.dto.request.AuthorSaveRequest;
import patika.authorservice.entity.dto.response.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorConverter {
    public static Author toAuthor(AuthorSaveRequest request) {
        return Author.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .bio(request.getBio())
                .build();
    }

    public static AuthorResponse toAuthorResponse(Author author, List<BookResponse> books, List<MagazineResponse> magazines) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .bio(author.getBio())
                .books(AuthorConverter.toListAuthorBookResponse(books))
                .magazines(AuthorConverter.toListAuthorMagazineResponse(magazines))
                .build();
    }

    public static AuthorBookResponse toAuthorBookResponse(BookResponse bookResponse) {
        return AuthorBookResponse.builder()
                .id(bookResponse.getId())
                .name(bookResponse.getName())
                .amount(bookResponse.getAmount())
                .description(bookResponse.getDescription())
                .publisherId(bookResponse.getPublisherId())
                .build();
    }

    public static List<AuthorBookResponse> toListAuthorBookResponse(List<BookResponse> bookResponses) {
        return bookResponses.stream()
                .map(AuthorConverter::toAuthorBookResponse)
                .toList();
    }

    public static AuthorMagazineResponse toAuthorMagazineResponse(MagazineResponse magazineResponse) {
        return AuthorMagazineResponse.builder()
                .id(magazineResponse.getId())
                .name(magazineResponse.getName())
                .amount(magazineResponse.getAmount())
                .description(magazineResponse.getDescription())
                .publishDate(magazineResponse.getPublishDate())
                .publisherId(magazineResponse.getPublisherId())
                .build();
    }

    public static List<AuthorMagazineResponse> toListAuthorMagazineResponse(List<MagazineResponse> magazineResponses) {
        return magazineResponses.stream()
                .map(AuthorConverter::toAuthorMagazineResponse)
                .toList();
    }
}
