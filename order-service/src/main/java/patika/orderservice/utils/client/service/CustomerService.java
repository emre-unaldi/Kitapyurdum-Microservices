package patika.orderservice.utils.client.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patika.orderservice.entity.dto.response.CustomerResponse;
import patika.orderservice.utils.client.CustomerServiceClient;
import patika.orderservice.utils.result.GenericResponse;

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
public class CustomerService {
    private final CustomerServiceClient customerServiceClient;

    @Cacheable(value = "order_customer", key = "#customerId")
    public CustomerResponse findById(Integer customerId) {
        try {
            ResponseEntity<GenericResponse<CustomerResponse>> response = customerServiceClient.findById(customerId);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error customer response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return null;
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return null;
        }
    }
}