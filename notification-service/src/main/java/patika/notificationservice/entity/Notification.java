package patika.notificationservice.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.CustomerDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.InvoiceDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.ProductDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@Document(collection = "notifications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Field("notification_type")
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    @Field("notification_status")
    private NotificationStatus notificationStatus;

    @Field("product_dto")
    private ProductDto product;

    @Field("customer_dto")
    private CustomerDto customer;

    @Field("invoice_dto")
    private InvoiceDto invoice;
}
