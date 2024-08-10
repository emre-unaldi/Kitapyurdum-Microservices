package patika.invoiceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.invoiceservice.entity.dto.request.InvoiceSaveRequest;
import patika.invoiceservice.entity.dto.response.InvoiceResponse;
import patika.invoiceservice.service.InvoiceService;
import patika.invoiceservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<GenericResponse<InvoiceResponse>> save(@RequestBody InvoiceSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(invoiceService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<InvoiceResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(invoiceService.findAll()));
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<GenericResponse<InvoiceResponse>> findById(@PathVariable Integer invoiceId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(invoiceService.findById(invoiceId)));
    }
}
