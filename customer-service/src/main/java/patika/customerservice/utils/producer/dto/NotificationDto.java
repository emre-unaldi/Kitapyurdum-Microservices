package patika.customerservice.utils.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.customerservice.utils.producer.dto.enums.NotificationStatus;
import patika.customerservice.utils.producer.dto.enums.NotificationType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private CustomerDto customerDto;
    private ProductDto productDto;
    private InvoiceDto invoiceDto;
}
