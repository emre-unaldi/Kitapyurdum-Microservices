package patika.orderservice.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceSaveRequest {
    private Integer orderId;
}
