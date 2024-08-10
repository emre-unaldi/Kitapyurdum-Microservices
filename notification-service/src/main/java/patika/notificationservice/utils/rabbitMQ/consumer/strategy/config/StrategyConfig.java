package patika.notificationservice.utils.rabbitMQ.consumer.strategy.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;
import patika.notificationservice.utils.rabbitMQ.consumer.strategy.NotificationStrategy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {
    private final List<NotificationStrategy> notificationStrategies;

    @Bean
    public Map<NotificationType, NotificationStrategy> sendNotificationByType() {
        Map<NotificationType, NotificationStrategy> messagesByType = new EnumMap<>(NotificationType.class);
        notificationStrategies.forEach(notificationStrategy -> messagesByType.put(notificationStrategy.notificationType(), notificationStrategy));
        return messagesByType;
    }
}
