package patika.orderservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import patika.orderservice.entity.dto.request.InvoiceSaveRequest;
import patika.orderservice.entity.dto.response.InvoiceResponse;
import patika.orderservice.utils.result.GenericResponse;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@FeignClient(name = "invoice-service", url = "http://localhost:8080")
public interface InvoiceServiceClient {
    @PostMapping("/api/v1/invoices")
    ResponseEntity<GenericResponse<InvoiceResponse>> save(@RequestBody InvoiceSaveRequest request);
}
