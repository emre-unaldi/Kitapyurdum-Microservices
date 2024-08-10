package patika.notificationservice.utils.rabbitMQ.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class InvoiceDto {
    private Integer id;
    private Double amount;
    private LocalDateTime createdDatetime;
}
