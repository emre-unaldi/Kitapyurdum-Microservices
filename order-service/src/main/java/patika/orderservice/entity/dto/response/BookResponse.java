package patika.orderservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class BookResponse {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private String description;
    private Integer publisherId;
    private Integer authorId;
}
