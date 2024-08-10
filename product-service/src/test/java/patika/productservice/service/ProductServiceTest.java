package patika.productservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import patika.productservice.entity.Book;
import patika.productservice.entity.Magazine;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.BookResponse;
import patika.productservice.entity.dto.response.MagazineResponse;
import patika.productservice.repository.BookRepository;
import patika.productservice.repository.MagazineRepository;
import patika.productservice.repository.spesification.ProductSpecification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private MagazineService magazineService;

    @InjectMocks
    private BookService bookService;

    @Mock
    private MagazineRepository magazineRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ProductSpecification productSpecification;

    private ProductSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new ProductSearchRequest("Magazine", BigDecimal.valueOf(100), "1", "asc");
    }

    @Test
    void search_shouldReturnExpectedListOfMagazineResponses() {
        Magazine magazine = new Magazine();
        magazine.setAmount(BigDecimal.valueOf(100));
        magazine.setName("Magazine 1");

        List<Magazine> magazines = Collections.singletonList(magazine);
        Page<Magazine> expectedPage = new PageImpl<>(magazines);

        when(magazineRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        when(ProductSpecification.initMagazineSpecification(any(ProductSearchRequest.class)))
                .thenReturn((root, query, cb) -> cb.equal(root.get("name"), "Magazine 1"));

        List<MagazineResponse> result = magazineService.search(request);

        assertThat(result).hasSize(1);
        MagazineResponse magazineResponse = result.getFirst();
        assertThat(magazineResponse.getAmount()).isEqualTo(BigDecimal.valueOf(100));
        assertThat(magazineResponse.getName()).isEqualTo("Magazine 1");
    }

    @Test
    void search_shouldReturnExpectedListOfBookResponses() {
        Book book = new Book();
        book.setAmount(BigDecimal.valueOf(200));
        book.setName("Book 1");

        List<Book> books = Collections.singletonList(book);
        Page<Book> expectedPage = new PageImpl<>(books);

        when(bookRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        when(ProductSpecification.initBookSpecification(any(ProductSearchRequest.class)))
                .thenReturn((root, query, cb) -> cb.equal(root.get("name"), "Book 1"));

        List<BookResponse> result = bookService.search(request);

        assertThat(result).hasSize(1);
        BookResponse bookResponse = result.getFirst();
        assertThat(bookResponse.getAmount()).isEqualTo(BigDecimal.valueOf(200));
        assertThat(bookResponse.getName()).isEqualTo("Book 1");
    }
}
