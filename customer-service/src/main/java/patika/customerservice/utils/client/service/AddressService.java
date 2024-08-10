package patika.customerservice.utils.client.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patika.customerservice.entity.dto.request.AddressIdsRequest;
import patika.customerservice.entity.dto.response.AddressResponse;
import patika.customerservice.utils.client.AddressServiceClient;
import patika.customerservice.utils.result.GenericResponse;

import java.util.Collections;
import java.util.List;

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
public class AddressService {
    private final AddressServiceClient addressServiceClient;

    public List<AddressResponse> filterByIdList(List<Integer> addressIds) {
        try {
            AddressIdsRequest addressIdsRequest = AddressIdsRequest.builder().addressIds(addressIds).build();
            ResponseEntity<GenericResponse<List<AddressResponse>>> response = addressServiceClient.filterByIdList(addressIdsRequest);

            if (response.getBody() == null || !HttpStatus.OK.equals(response.getBody().getHttpStatus())) {
                log.error("Error address response message: {}", response.getBody() != null ? response.getBody().getMessage() : "Response body is null");
                return Collections.emptyList();
            }

            return response.getBody().getData();
        } catch (FeignException e) {
            log.error("FeignException: Status {}, Error: {}", e.status(), e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
