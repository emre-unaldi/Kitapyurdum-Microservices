package patika.orderservice.entity.dto.request;

import lombok.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseSearchRequest {

    private int page;
    private int size;

}
