package patika.notificationservice.utils.rabbitMQ.consumer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagazineProductResponse {
    private String name;
    private String description;
}
