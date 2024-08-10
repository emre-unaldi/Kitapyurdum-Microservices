package patika.notificationservice.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import patika.notificationservice.entity.Notification;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.repository.NotificationRepository;
import patika.notificationservice.service.NotificationService;
import patika.notificationservice.utils.constants.NotificationConstants;
import patika.notificationservice.utils.converter.NotificationConverter;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public NotificationResponse save(NotificationDto notificationDto) {
        Notification notification = NotificationConverter.toNotification(notificationDto);
        notificationRepository.save(notification);

        return NotificationConverter.toNotificationResponse(notification);
    }

    @Override
    public NotificationResponse findById(String notificationId) {
        log.info("Finding notification by id: {}", notificationId);

        return notificationRepository
                .findById(notificationId)
                .map(NotificationConverter::toNotificationResponse)
                .orElseThrow(() -> new RuntimeException(NotificationConstants.NOT_FOUND));
    }

    @Override
    public List<NotificationResponse> findAll() {
        log.info("Finding all notifications");

        return notificationRepository
                .findAll()
                .stream()
                .map(NotificationConverter::toNotificationResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> findByNotificationStatus(NotificationStatus notificationStatus) {
        log.info("Finding notifications by notification status: {}", notificationStatus);

        return notificationRepository
                .findByNotificationStatus(notificationStatus)
                .stream()
                .map(NotificationConverter::toNotificationResponse)
                .toList();
    }
}
