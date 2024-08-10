package patika.notificationservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import patika.notificationservice.entity.Notification;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.repository.NotificationRepository;
import patika.notificationservice.service.Impl.NotificationServiceImpl;
import patika.notificationservice.utils.ObjectFactory;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.CustomerDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.InvoiceDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.NotificationDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.ProductDto;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.BookProductResponse;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.response.MagazineProductResponse;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {
    private static BookProductResponse bookProductResponse;
    private static MagazineProductResponse magazineProductResponse;
    private static ProductDto productDto;
    private static CustomerDto customerDto;
    private static InvoiceDto invoiceDto;
    private static NotificationDto notificationDto;
    private static NotificationResponse notificationResponse;
    private static Notification notification;
    private final String nonExistentNotificationId = "-987da9s8s89s89";

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeAll
    static void setUp() {
        bookProductResponse = ObjectFactory.getInstance().getBookProductResponse();
        magazineProductResponse = ObjectFactory.getInstance().getMagazineProductResponse();
        productDto = ObjectFactory.getInstance().getProductDto();
        customerDto = ObjectFactory.getInstance().getCustomerDto();
        invoiceDto = ObjectFactory.getInstance().getInvoiceDto();
        notificationDto = ObjectFactory.getInstance().getNotificationDto();
        notificationResponse = ObjectFactory.getInstance().getNotificationResponse();
        notification = ObjectFactory.getInstance().getNotification();
    }

    @Test
    void givenNotificationDto_whenSave_thenNotificationShouldBeSaved() {
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        NotificationResponse result =  notificationService.save(notificationDto);

        assertNotNull(result, "Notification should not be null");

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void givenNotificationId_whenFindById_thenNotificationShouldBeFound() {
        when(notificationRepository.findById(notification.getId())).thenReturn(Optional.of(notification));

        NotificationResponse result = notificationService.findById(notification.getId());
        assertEquals(notificationResponse, result, "Notification and result object must be the same");

        verify(notificationRepository, times(1)).findById(notification.getId());
    }

    @Test
    void givenNotificationId_whenFindById_NotificationNotFound() {
        when(notificationRepository.findById(nonExistentNotificationId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> notificationService.findById(nonExistentNotificationId));

        verify(notificationRepository, times(1)).findById(nonExistentNotificationId);
    }

    @Test
    void givenNotificationList_whenFindAll_thenAllNotificationsShouldBeReturned() {
        when(notificationRepository.findAll()).thenReturn(List.of(notification));

        List<NotificationResponse> result = notificationService.findAll();

        assertFalse(result.isEmpty(), "Result list should not be empty.");
        assertEquals(notificationResponse, result.getFirst(), "Result should match the expected notification response.");

        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void testFindByNotificationStatus_NotificationsReturned() {
        NotificationStatus status = NotificationStatus.SUCCESS;

        when(notificationRepository.findByNotificationStatus(status)).thenReturn(List.of(notification));

        List<NotificationResponse> result = notificationService.findByNotificationStatus(status);

        assertNotNull(result,"Result list should not be empty.");
        assertEquals(notificationResponse, result.getFirst(), "Result should match the expected notification response.");

        verify(notificationRepository, times(1)).findByNotificationStatus(status);
    }
}
