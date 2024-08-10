package patika.customerservice.entity.dto.request;

import lombok.Getter;
import patika.customerservice.entity.enums.AccountType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Getter
public class CustomerChangeAccountTypeRequest {
    private String email;
    private AccountType accountType;
}
