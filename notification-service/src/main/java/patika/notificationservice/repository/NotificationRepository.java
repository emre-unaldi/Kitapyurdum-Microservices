package patika.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import patika.notificationservice.entity.Notification;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByNotificationStatus(NotificationStatus status);

}
