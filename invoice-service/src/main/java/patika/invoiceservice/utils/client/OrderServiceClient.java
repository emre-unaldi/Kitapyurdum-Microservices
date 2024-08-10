package patika.invoiceservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import patika.invoiceservice.entity.dto.response.OrderResponse;
import patika.invoiceservice.utils.result.GenericResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@FeignClient(name = "order-service", url = "http://localhost:8080")
public interface OrderServiceClient {
    @GetMapping("/api/v1/orders/{orderId}")
    ResponseEntity<GenericResponse<OrderResponse>> findById(@PathVariable Integer orderId);
}
