package patika.publisherservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.publisherservice.entity.dto.request.PublisherSaveRequest;
import patika.publisherservice.entity.dto.response.PublisherResponse;
import patika.publisherservice.entity.dto.response.PublisherProductResponse;
import patika.publisherservice.service.PublisherService;
import patika.publisherservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<GenericResponse<PublisherResponse>> save(@RequestBody PublisherSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(publisherService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<PublisherResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(publisherService.findAll()));
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<GenericResponse<PublisherResponse>> findById(@PathVariable Integer publisherId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(publisherService.findById(publisherId)));
    }

    @GetMapping("/productsById/{publisherId}")
    public ResponseEntity<GenericResponse<PublisherProductResponse>> filterProductsById(@PathVariable Integer publisherId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(publisherService.filterProductsById(publisherId)));
    }

    @GetMapping("/productsByName/{publisherName}")
    public ResponseEntity<GenericResponse<PublisherProductResponse>> filterProductsByName(@PathVariable String publisherName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(publisherService.filterProductsByName(publisherName)));
    }
}
