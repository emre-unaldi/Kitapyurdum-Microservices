package patika.customerservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import patika.customerservice.entity.enums.AccountType;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@Builder
@AllArgsConstructor
public class CustomerAllResponse {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer credit;
    private String phoneNumber;
    private Boolean isActive;
    private AccountType accountType;
    private List<AddressResponse> addresses;
    private List<OrderResponse> orders;
}
