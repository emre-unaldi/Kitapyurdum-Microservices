package patika.orderservice.utils.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.orderservice.entity.dto.response.BookProductResponse;
import patika.orderservice.entity.dto.response.MagazineProductResponse;

import java.math.BigDecimal;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private List<BookProductResponse> books;
    private List<MagazineProductResponse> magazines;
    private BigDecimal totalAmount;
}
