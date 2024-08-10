package patika.customerservice.utils.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import patika.customerservice.entity.dto.request.AddressIdsRequest;
import patika.customerservice.entity.dto.response.AddressResponse;
import patika.customerservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@FeignClient(name = "address-service", url = "http://localhost:8080")
public interface AddressServiceClient {
    @PostMapping("/api/v1/address/filterByIdList")
    ResponseEntity<GenericResponse<List<AddressResponse>>> filterByIdList(@RequestBody AddressIdsRequest request);
}
