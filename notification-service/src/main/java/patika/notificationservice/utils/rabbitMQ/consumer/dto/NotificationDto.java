package patika.notificationservice.utils.rabbitMQ.consumer.dto;

import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private ProductDto productDto;
    private CustomerDto customerDto;
    private InvoiceDto invoiceDto;
}
