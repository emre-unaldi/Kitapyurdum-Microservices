package patika.notificationservice.utils.rabbitMQ.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.service.NotificationService;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.strategy.context.NotificationContext;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationContext notificationContext;
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.notification.queue}")
    public void sendNotification(NotificationDto notificationDto) {
        notificationContext.sendMessage(notificationDto);

        NotificationResponse notificationResponse = notificationService.save(notificationDto);
        log.info("Notification saved: {}", notificationResponse);
    }
}
