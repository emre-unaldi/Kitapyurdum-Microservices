package patika.customerservice.utils.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import patika.customerservice.utils.configuration.RabbitMQProducerConfiguration;
import patika.customerservice.utils.producer.dto.NotificationDto;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationProducer {
    private final AmqpTemplate rabbitTemplate;
    private final RabbitMQProducerConfiguration rabbitConfig;

    public void sendNotification(NotificationDto notificationDto) {
        rabbitTemplate.convertAndSend(rabbitConfig.getExchange(), rabbitConfig.getRoutingKey(), notificationDto);
        log.info("Notification sent : {}", notificationDto);
    }
}
