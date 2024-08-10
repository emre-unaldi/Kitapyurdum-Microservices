package patika.authorservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.authorservice.entity.dto.request.AuthorIdsRequest;
import patika.authorservice.entity.dto.request.AuthorSaveRequest;
import patika.authorservice.entity.dto.response.AuthorResponse;
import patika.authorservice.service.AuthorService;
import patika.authorservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<GenericResponse<AuthorResponse>> save(@RequestBody AuthorSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(authorService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<AuthorResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(authorService.findAll()));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<GenericResponse<AuthorResponse>> findById(@PathVariable Integer authorId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(authorService.findById(authorId)));
    }

    @PostMapping("/filterByIdList")
    public ResponseEntity<GenericResponse<List<AuthorResponse>>> filterByIdList(@RequestBody AuthorIdsRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(authorService.filterByIdList(request)));
    }
}
