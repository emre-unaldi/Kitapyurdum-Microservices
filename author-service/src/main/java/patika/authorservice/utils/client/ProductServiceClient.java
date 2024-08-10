package patika.authorservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import patika.authorservice.entity.dto.response.BookResponse;
import patika.authorservice.entity.dto.response.MagazineResponse;
import patika.authorservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductServiceClient {
    @GetMapping("/api/v1/books/author/{authorId}")
    ResponseEntity<GenericResponse<List<BookResponse>>> filterByBookAuthorId(@PathVariable Integer authorId);

    @GetMapping("/api/v1/magazines/author/{authorId}")
    ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByMagazineAuthorId(@PathVariable Integer authorId);
}
