package patika.productservice.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchRequest extends BaseSearchRequest {
    private String name;
    private BigDecimal amount;
    private String publisherId;
    private String sort;

    public ProductSearchRequest(int page, int size, String name, BigDecimal amount, String publisherId, String sort) {
        super(page, size);
        this.name = name;
        this.amount = amount;
        this.publisherId = publisherId;
        this.sort = sort;
    }

    public ProductSearchRequest(String name, BigDecimal amount, String publisherId, String sort) {
        this.name = name;
        this.amount = amount;
        this.publisherId = publisherId;
        this.sort = sort;
    }
}
