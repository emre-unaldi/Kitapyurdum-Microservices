package patika.addressservice.service;

import patika.addressservice.entity.dto.request.AddressIdsRequest;
import patika.addressservice.entity.dto.request.AddressSaveRequest;
import patika.addressservice.entity.dto.response.AddressResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface AddressService {
    AddressResponse save(AddressSaveRequest request);
    List<AddressResponse> findAll();
    AddressResponse findById(Integer addressId);
    List<AddressResponse> filterByIdList(AddressIdsRequest request);
}
