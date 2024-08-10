package patika.notificationservice.utils.rabbitMQ.consumer.strategy;

import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;

public interface NotificationStrategy {
    void sendMessage(NotificationDto notification);

    NotificationType notificationType();
}
