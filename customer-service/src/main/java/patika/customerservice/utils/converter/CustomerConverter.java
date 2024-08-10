package patika.customerservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.customerservice.entity.Customer;
import patika.customerservice.entity.dto.request.CustomerSaveRequest;
import patika.customerservice.entity.dto.response.AddressResponse;
import patika.customerservice.entity.dto.response.CustomerAllResponse;
import patika.customerservice.entity.dto.response.CustomerResponse;
import patika.customerservice.entity.dto.response.OrderResponse;
import patika.customerservice.entity.enums.AccountType;
import patika.customerservice.utils.HashUtil;
import patika.customerservice.utils.producer.dto.CustomerDto;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {

    public static Customer toCustomer(CustomerSaveRequest request) {
        String hashedPassword = HashUtil.generate(request.getPassword());

        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(hashedPassword)
                .credit(request.getCredit())
                .phoneNumber(request.getPhoneNumber())
                .isActive(Boolean.TRUE)
                .accountType(AccountType.STANDARD)
                .addressIds(request.getAddressIds())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .phoneNumber(customer.getPhoneNumber())
                .isActive(customer.getIsActive())
                .accountType(customer.getAccountType())
                .addressIds(customer.getAddressIds())
                .build();
    }

    public static CustomerAllResponse toCustomerAllResponse(Customer customer, List<AddressResponse> addressResponses, List<OrderResponse> orderResponses) {
        return CustomerAllResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .phoneNumber(customer.getPhoneNumber())
                .isActive(customer.getIsActive())
                .accountType(customer.getAccountType())
                .addresses(addressResponses)
                .orders(orderResponses)
                .build();
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .phoneNumber(customer.getPhoneNumber())
                .isActive(customer.getIsActive())
                .accountType(customer.getAccountType())
                .build();
    }
}
