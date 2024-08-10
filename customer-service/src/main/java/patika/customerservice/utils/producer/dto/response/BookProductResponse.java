package patika.customerservice.utils.producer.dto.response;

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
public class BookProductResponse {
    private String name;
    private String description;
}
