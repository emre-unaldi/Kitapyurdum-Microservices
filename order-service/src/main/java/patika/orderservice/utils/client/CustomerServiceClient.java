package patika.orderservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import patika.orderservice.entity.dto.response.CustomerResponse;
import patika.orderservice.utils.result.GenericResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@FeignClient(name = "customer-service", url = "http://localhost:8080")
public interface CustomerServiceClient {
    @GetMapping("/api/v1/customers/{customerId}")
    ResponseEntity<GenericResponse<CustomerResponse>> findById(@PathVariable Integer customerId);
}
