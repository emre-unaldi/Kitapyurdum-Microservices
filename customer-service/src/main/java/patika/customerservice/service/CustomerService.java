package patika.customerservice.service;

import patika.customerservice.entity.dto.request.CustomerChangeEmailAndAddressesRequest;
import patika.customerservice.entity.dto.request.CustomerSaveRequest;
import patika.customerservice.entity.dto.response.CustomerAllResponse;
import patika.customerservice.entity.dto.response.CustomerResponse;
import patika.customerservice.entity.enums.AccountType;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface CustomerService {
    CustomerResponse save(CustomerSaveRequest request);
    List<CustomerAllResponse> findAll();
    CustomerAllResponse findById(Integer customerId);
    CustomerAllResponse findByEmail(String email);
    CustomerAllResponse changeAccountType(String email, AccountType accountType);
    CustomerAllResponse changeAccountTypeByCredit(String email);
    CustomerAllResponse changeEmailAndAddresses(CustomerChangeEmailAndAddressesRequest request);
}