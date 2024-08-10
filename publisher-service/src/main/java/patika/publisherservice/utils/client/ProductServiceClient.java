package patika.publisherservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import patika.publisherservice.entity.dto.response.BookResponse;
import patika.publisherservice.entity.dto.response.MagazineResponse;
import patika.publisherservice.utils.result.GenericResponse;

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
    @GetMapping("/api/v1/books/publisher/{publisherId}")
    ResponseEntity<GenericResponse<List<BookResponse>>> filterByBookPublisherId(@PathVariable Integer publisherId);

    @GetMapping("/api/v1/magazines/publisher/{publisherId}")
    ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByMagazinePublisherId(@PathVariable Integer publisherId);
}
