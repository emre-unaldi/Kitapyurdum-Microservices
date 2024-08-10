package patika.orderservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class InvoiceResponse {
    private Integer id;
    private Double amount;
    private LocalDateTime createdDatetime;
    private OrderResponse order;
}
