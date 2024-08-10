package patika.notificationservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.CustomerDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.InvoiceDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.ProductDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private String id;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private ProductDto product;
    private CustomerDto customer;
    private InvoiceDto invoice;
}
