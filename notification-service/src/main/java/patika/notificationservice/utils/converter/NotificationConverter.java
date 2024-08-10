package patika.notificationservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.notificationservice.entity.Notification;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConverter {

    public static Notification toNotification(NotificationDto notificationDto) {
        return Notification.builder()
                .notificationType(notificationDto.getNotificationType())
                .notificationStatus(notificationDto.getNotificationStatus())
                .product(notificationDto.getProductDto())
                .customer(notificationDto.getCustomerDto())
                .invoice(notificationDto.getInvoiceDto())
                .build();
    }

    public static NotificationResponse toNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .notificationType(notification.getNotificationType())
                .notificationStatus(notification.getNotificationStatus())
                .product(notification.getProduct())
                .customer(notification.getCustomer())
                .invoice(notification.getInvoice())
                .build();
    }
}
