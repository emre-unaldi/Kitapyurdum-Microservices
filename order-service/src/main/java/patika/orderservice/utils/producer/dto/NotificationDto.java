package patika.orderservice.utils.producer.dto;

import lombok.*;
import patika.orderservice.utils.producer.dto.enums.NotificationStatus;
import patika.orderservice.utils.producer.dto.enums.NotificationType;

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
    private ProductDto productDto;
    private CustomerDto customerDto;
    private InvoiceDto invoiceDto;
}