package patika.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.productservice.entity.dto.request.BookIdsRequest;
import patika.productservice.entity.dto.request.BookSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.BookResponse;
import patika.productservice.entity.dto.response.MagazineResponse;
import patika.productservice.service.BookService;
import patika.productservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<GenericResponse<BookResponse>> save(@RequestBody BookSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(bookService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<BookResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.findAll()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<GenericResponse<BookResponse>> findById(@PathVariable Integer bookId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.findById(bookId)));
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<GenericResponse<List<BookResponse>>> filterByPublisherId(@PathVariable Integer publisherId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.filterByPublisherId(publisherId)));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<GenericResponse<List<BookResponse>>> filterByAuthorId(@PathVariable Integer authorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.filterByAuthorId(authorId)));
    }

    @PostMapping("/filterByIdList")
    public ResponseEntity<GenericResponse<List<BookResponse>>> filterByIdList(@RequestBody BookIdsRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.filterByIdList(request)));
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<BookResponse>>> search(@RequestBody ProductSearchRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(bookService.search(request)));
    }
}
