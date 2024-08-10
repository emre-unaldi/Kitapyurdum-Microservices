package patika.notificationservice.utils.rabbitMQ.consumer.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;
import patika.notificationservice.utils.rabbitMQ.consumer.strategy.NotificationStrategy;

@Slf4j
@Component
public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendMessage(NotificationDto notification) {
        log.info("Push notification sent: {}", notification);
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.PUSH_NOTIFICATION;
    }
}

