package patika.notificationservice.utils.rabbitMQ.consumer.strategy.context;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;
import patika.notificationservice.utils.rabbitMQ.consumer.strategy.NotificationStrategy;
import patika.notificationservice.utils.rabbitMQ.consumer.strategy.exception.NotFoundNotificationStrategy;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
@Slf4j
public class NotificationContext {
    private final Map<NotificationType, NotificationStrategy> sendNotificationByType;

    public void sendMessage(NotificationDto notification) throws NotFoundNotificationStrategy {
        NotificationStrategy notificationStrategy = sendNotificationByType.getOrDefault(notification.getNotificationType(), null);
        if (Objects.isNull(notificationStrategy)) {
            throw new NotFoundNotificationStrategy("Notification Type not found. type: " + notification.getNotificationType());
        }
        notificationStrategy.sendMessage(notification);
    }
}
