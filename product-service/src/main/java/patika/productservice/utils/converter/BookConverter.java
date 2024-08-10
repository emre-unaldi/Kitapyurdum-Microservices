package patika.productservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.productservice.entity.Book;
import patika.productservice.entity.dto.request.BookSaveRequest;
import patika.productservice.entity.dto.response.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookConverter {
    public static Book toBook(BookSaveRequest request) {
        return Book.builder()
                .name(request.getName())
                .amount(request.getAmount())
                .description(request.getDescription())
                .publisherId(request.getPublisherId())
                .authorId(request.getAuthorId())
                .build();
    }

    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .amount(book.getAmount())
                .description(book.getDescription())
                .authorId(book.getAuthorId())
                .publisherId(book.getPublisherId())
                .build();
    }

    public static List<BookResponse> toListBookResponse(List<Book> books) {
        return books.stream()
                .map(BookConverter::toBookResponse)
                .toList();
    }
}

