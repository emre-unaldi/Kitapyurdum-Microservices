package patika.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.productservice.entity.dto.request.MagazineIdsRequest;
import patika.productservice.entity.dto.request.MagazineSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.MagazineResponse;
import patika.productservice.service.MagazineService;
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
@RequestMapping("/api/v1/magazines")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @PostMapping
    public ResponseEntity<GenericResponse<MagazineResponse>> save(@RequestBody MagazineSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(magazineService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<MagazineResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.findAll()));
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<GenericResponse<MagazineResponse>> findById(@PathVariable Integer magazineId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.findById(magazineId)));
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByPublisherId(@PathVariable Integer publisherId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.filterByPublisherId(publisherId)));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByAuthorId(@PathVariable Integer authorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.filterByAuthorId(authorId)));
    }

    @PostMapping("/filterByIdList")
    public ResponseEntity<GenericResponse<List<MagazineResponse>>> filterByIdList(@RequestBody MagazineIdsRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.filterByIdList(request)));
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<MagazineResponse>>> search(@RequestBody ProductSearchRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(magazineService.search(request)));
    }
}
