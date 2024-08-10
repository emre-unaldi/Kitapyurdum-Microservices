package patika.notificationservice.utils;

import org.instancio.Instancio;
import patika.notificationservice.entity.Notification;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.CustomerDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.InvoiceDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.ProductDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.AccountType;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationType;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.BookProductResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.MagazineProductResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.instancio.Select.field;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
public class ObjectFactory {
    private static ObjectFactory instance;
    private BookProductResponse bookProductResponse;
    private MagazineProductResponse magazineProductResponse;
    private ProductDto productDto;
    private CustomerDto customerDto;
    private InvoiceDto invoiceDto;
    private NotificationDto notificationDto;
    private NotificationResponse notificationResponse;
    private Notification notification;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public BookProductResponse getBookProductResponse() {
        if (bookProductResponse == null) {
            bookProductResponse = Instancio.of(BookProductResponse.class)
                    .set(field("name"), "Book 1")
                    .set(field("description"), "Book Description 1")
                    .create();
        }

        return bookProductResponse;
    }

    public MagazineProductResponse getMagazineProductResponse() {
        if (magazineProductResponse == null) {
            magazineProductResponse = Instancio.of(MagazineProductResponse.class)
                    .set(field("name"), "Book 1")
                    .set(field("description"), "Book Description 1")
                    .create();
        }

        return magazineProductResponse;
    }

    public ProductDto getProductDto() {
        if (productDto == null) {
            productDto = Instancio.of(ProductDto.class)
                    .set(field("books"), List.of(getBookProductResponse()))
                    .set(field("magazines"), List.of(getMagazineProductResponse()))
                    .set(field("totalAmount"), BigDecimal.valueOf(1250.0))
                    .create();
        }

        return productDto;
    }

    public CustomerDto getCustomerDto() {
        if (customerDto == null) {
            customerDto = Instancio.of(CustomerDto.class)
                    .set(field("id"), 1)
                    .set(field("name"), "Emre")
                    .set(field("surname"), "Ünaldı")
                    .set(field("email"), "emre.unaldi@gmail.com")
                    .set(field("password"), "password1234")
                    .set(field("credit"), 1450)
                    .set(field("phoneNumber"), "05078700000")
                    .set(field("isActive"), true)
                    .set(field("accountType"), AccountType.PLATINUM)
                    .create();
        }

        return customerDto;
    }

    public InvoiceDto getInvoiceDto() {
        if(invoiceDto == null) {
            invoiceDto = Instancio.of(InvoiceDto.class)
                    .set(field("id"), 1)
                    .set(field("amount"), 1250.0)
                    .set(field("createdDatetime"), LocalDateTime.now())
                    .create();
        }

        return invoiceDto;
    }

    public NotificationDto getNotificationDto() {
        if(notificationDto == null) {
            notificationDto = Instancio.of(NotificationDto.class)
                    .set(field("notificationType"), NotificationType.MAIL)
                    .set(field("notificationStatus"), NotificationStatus.SUCCESS)
                    .set(field("productDto"), getProductDto())
                    .set(field("customerDto"), getCustomerDto())
                    .set(field("invoiceDto"), getInvoiceDto())
                    .create();
        }

        return notificationDto;
    }

    public NotificationResponse getNotificationResponse() {
        if(notificationResponse == null) {
            notificationResponse = Instancio.of(NotificationResponse.class)
                    .set(field("id"), "s12kjh12k3412jk")
                    .set(field("notificationType"), NotificationType.MAIL)
                    .set(field("notificationStatus"), NotificationStatus.SUCCESS)
                    .set(field("product"), getProductDto())
                    .set(field("customer"), getCustomerDto())
                    .set(field("invoice"), getInvoiceDto())
                    .create();
        }

        return notificationResponse;
    }

    public Notification getNotification() {
        if(notification == null) {
            notification = Instancio.of(Notification.class)
                    .set(field("id"), "s12kjh12k3412jk")
                    .set(field("notificationType"), NotificationType.MAIL)
                    .set(field("notificationStatus"), NotificationStatus.SUCCESS)
                    .set(field("product"), getProductDto())
                    .set(field("customer"), getCustomerDto())
                    .set(field("invoice"), getInvoiceDto())
                    .create();
        }

        return notification;
    }
}
