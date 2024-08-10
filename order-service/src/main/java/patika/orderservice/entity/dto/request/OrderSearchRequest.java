package patika.orderservice.entity.dto.request;

import lombok.*;
import patika.orderservice.entity.enums.OrderStatus;

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
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchRequest extends BaseSearchRequest{

    private OrderStatus orderStatus;

}
