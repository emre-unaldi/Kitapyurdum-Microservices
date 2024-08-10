package patika.addressservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.addressservice.entity.Address;
import patika.addressservice.entity.dto.request.AddressSaveRequest;
import patika.addressservice.entity.dto.response.AddressResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressConverter {
    public static Address toAddress(AddressSaveRequest request) {
        return Address.builder()
                .title(request.getTitle())
                .province(request.getProvince())
                .description(request.getDescription())
                .build();
    }

    public static AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .title(address.getTitle())
                .province(address.getProvince())
                .description(address.getDescription())
                .build();
    }

    public static List<AddressResponse> toListAddressResponse(List<Address> addresses) {
        return addresses.stream()
                .map(AddressConverter::toAddressResponse)
                .collect(Collectors.toList());
    }
}

