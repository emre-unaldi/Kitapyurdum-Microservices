package patika.customerservice.utils;

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
public class HashUtil {
    public static String generate(String password) {
        String SALT = "customSalt_customSalt_customSalt";
        String PEPPER = "customPepper_customPepper_customPepper";
        String saltedPassword = SALT + PEPPER + password;

        StringBuilder shiftedPassword = new StringBuilder();
        int shift = 3;

        for (char c : saltedPassword.toCharArray()) {
            shiftedPassword.append((char) (c + shift));
        }

        int hash = 0;
        for (char c : shiftedPassword.toString().toCharArray()) {
            hash += c;
        }

        return Integer.toHexString(hash);
    }
}

