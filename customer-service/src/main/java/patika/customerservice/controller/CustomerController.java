package patika.customerservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.customerservice.entity.dto.request.CustomerChangeAccountTypeRequest;
import patika.customerservice.entity.dto.request.CustomerChangeEmailAndAddressesRequest;
import patika.customerservice.entity.dto.request.CustomerSaveRequest;
import patika.customerservice.entity.dto.response.CustomerAllResponse;
import patika.customerservice.entity.dto.response.CustomerResponse;
import patika.customerservice.service.CustomerService;
import patika.customerservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<GenericResponse<CustomerResponse>> save(@RequestBody CustomerSaveRequest customerSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(customerService.save(customerSaveRequest)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<CustomerAllResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.findAll()));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<GenericResponse<CustomerAllResponse>> findById(@PathVariable Integer customerId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.findById(customerId)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GenericResponse<CustomerAllResponse>> findByEmail(@PathVariable String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.findByEmail(email)));
    }

    @PatchMapping("/accountType")
    public ResponseEntity<GenericResponse<CustomerAllResponse>> changeAccountType(@RequestBody CustomerChangeAccountTypeRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.changeAccountType(request.getEmail(), request.getAccountType())));
    }

    @PatchMapping("/accountTypeByCredit/{email}")
    public ResponseEntity<GenericResponse<CustomerAllResponse>> changeAccountTypeByCredit(@PathVariable String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.changeAccountTypeByCredit(email)));
    }

    @PatchMapping("/emailAndAddresses")
    public ResponseEntity<GenericResponse<CustomerAllResponse>> changeEmailAndAddresses(@RequestBody CustomerChangeEmailAndAddressesRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(customerService.changeEmailAndAddresses(request)));
    }
}

