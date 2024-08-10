package patika.notificationservice.utils.rabbitMQ.consumer.dto;

import lombok.*;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.BookProductResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.MagazineProductResponse;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private List<BookProductResponse> books;
    private List<MagazineProductResponse> magazines;
    private BigDecimal totalAmount;
}