package patika.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.addressservice.entity.dto.request.AddressIdsRequest;
import patika.addressservice.entity.dto.request.AddressSaveRequest;
import patika.addressservice.entity.dto.response.AddressResponse;
import patika.addressservice.service.AddressService;
import patika.addressservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<GenericResponse<AddressResponse>> save(@RequestBody AddressSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(addressService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<AddressResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(addressService.findAll()));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<GenericResponse<AddressResponse>> findById(@PathVariable Integer addressId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(addressService.findById(addressId)));
    }

    @PostMapping("/filterByIdList")
    public ResponseEntity<GenericResponse<List<AddressResponse>>> filterByIdList(@RequestBody AddressIdsRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(addressService.filterByIdList(request)));
    }
}
