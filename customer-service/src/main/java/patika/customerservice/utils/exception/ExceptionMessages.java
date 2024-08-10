package patika.customerservice.utils.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String CUSTOMER_NOT_FOUND = "Customer not found.";
    public static final String CUSTOMER_NOT_ACTIVE = "Customer is not active.";
    public static final String EMAIL_ALREADY_EXIST = "There is a user registered with this email";
}
