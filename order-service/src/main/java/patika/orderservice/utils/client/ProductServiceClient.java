package patika.orderservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import patika.orderservice.entity.dto.request.BookIdsRequest;
import patika.orderservice.entity.dto.request.MagazineIdsRequest;
import patika.orderservice.entity.dto.response.BookResponse;
import patika.orderservice.entity.dto.response.MagazineResponse;
import patika.orderservice.utils.result.GenericResponse;

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
    @PostMapping("/api/v1/books/filterByIdList")
    ResponseEntity<GenericResponse<List<BookResponse>>> filterByBookIdList(@RequestBody BookIdsRequest request);

    @PostMapping("/api/v1/magazines/filterByIdList")
    ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByMagazineIdList(@RequestBody MagazineIdsRequest request);
}
