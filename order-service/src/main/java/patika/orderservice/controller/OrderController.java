package patika.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.request.OrderSearchRequest;
import patika.orderservice.entity.dto.response.OrderResponse;
import patika.orderservice.service.OrderService;
import patika.orderservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<GenericResponse<OrderResponse>> save(@RequestBody OrderSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(orderService.save(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<OrderResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(orderService.findAll()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GenericResponse<OrderResponse>> findById(@PathVariable Integer orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(orderService.findById(orderId)));
    }

    @GetMapping("/filterByCustomerId/{customerId}")
    ResponseEntity<GenericResponse<List<OrderResponse>>> filterByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(orderService.filterByCustomerId(customerId)));
    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<OrderResponse>>> search(@RequestBody OrderSearchRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(orderService.search(request)));
    }
}

