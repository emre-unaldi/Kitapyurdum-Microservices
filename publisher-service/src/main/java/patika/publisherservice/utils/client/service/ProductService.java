package patika.publisherservice.utils.client.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patika.publisherservice.entity.dto.response.BookResponse;
import patika.publisherservice.entity.dto.response.MagazineResponse;
import patika.publisherservice.utils.client.ProductServiceClient;
import patika.publisherservice.utils.result.GenericResponse;

import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductServiceClient productServiceClient;

    public List<BookResponse> filterByBookPublisherId(Integer publisherId) {
        try {
            ResponseEntity<GenericResponse<List<BookResponse>>> response = productServiceClient.filterByBookPublisherId(publisherId);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error book response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return Collections.emptyList();
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<MagazineResponse> filterByMagazinePublisherId(Integer publisherId) {
        try {
            ResponseEntity<GenericResponse<List<MagazineResponse>>> response = productServiceClient.filterByMagazinePublisherId(publisherId);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error magazine response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return Collections.emptyList();
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
