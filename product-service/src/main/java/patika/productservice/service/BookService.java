package patika.productservice.service;

import patika.productservice.entity.dto.request.BookIdsRequest;
import patika.productservice.entity.dto.request.BookSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.BookResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface BookService {
    BookResponse save(BookSaveRequest request);
    List<BookResponse> findAll();
    BookResponse findById(Integer bookId);
    List<BookResponse> filterByIdList(BookIdsRequest request);
    List<BookResponse> filterByPublisherId(Integer publisherId);
    List<BookResponse> filterByAuthorId(Integer authorId);
    List<BookResponse> search(ProductSearchRequest request);
}