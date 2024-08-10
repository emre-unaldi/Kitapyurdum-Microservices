package patika.orderservice.utils.client.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patika.orderservice.entity.dto.request.InvoiceSaveRequest;
import patika.orderservice.entity.dto.response.InvoiceResponse;
import patika.orderservice.utils.client.InvoiceServiceClient;
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
public class InvoiceService {
    private final InvoiceServiceClient invoiceServiceClient;

    public InvoiceResponse save(Integer orderId) {
        try {
            InvoiceSaveRequest invoiceSaveRequest = InvoiceSaveRequest.builder().orderId(orderId).build();
            ResponseEntity<GenericResponse<InvoiceResponse>> response = invoiceServiceClient.save(invoiceSaveRequest);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error invoice response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return null;
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return null;
        }
    }
}
