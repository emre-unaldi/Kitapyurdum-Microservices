package patika.customerservice.entity.dto.request;

import lombok.Getter;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Getter
public class CustomerChangeEmailAndAddressesRequest {
    private Integer id;
    private String email;
    private List<Integer> addressIds;
}
