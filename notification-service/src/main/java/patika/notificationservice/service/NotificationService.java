package patika.notificationservice.service;

import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
public interface NotificationService {
    NotificationResponse save(NotificationDto notificationDto);
    NotificationResponse findById(String notificationId);
    List<NotificationResponse> findAll();
    List<NotificationResponse> findByNotificationStatus(NotificationStatus notificationStatus);
}
