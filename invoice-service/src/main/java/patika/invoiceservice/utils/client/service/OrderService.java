package patika.invoiceservice.utils.client.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patika.invoiceservice.entity.dto.response.OrderResponse;
import patika.invoiceservice.utils.client.OrderServiceClient;
import patika.invoiceservice.utils.result.GenericResponse;

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
public class OrderService {
    private final OrderServiceClient orderServiceClient;

    public OrderResponse findById(Integer orderId) {
        try {
            ResponseEntity<GenericResponse<OrderResponse>> response = orderServiceClient.findById(orderId);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error order response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return null;
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return null;
        }
    }
}
