package patika.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patika.notificationservice.entity.dto.NotificationResponse;
import patika.notificationservice.service.NotificationService;
import patika.notificationservice.utils.rabbitMQ.consumer.dto.enums.NotificationStatus;
import patika.notificationservice.utils.result.GenericResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@RestController
@RequestMapping("api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<NotificationResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(notificationService.findAll()));
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<GenericResponse<NotificationResponse>> findById(@PathVariable("notificationId") String notificationId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(notificationService.findById(notificationId)));
    }

    @GetMapping("/findByStatus/{notificationStatus}")
    public ResponseEntity<GenericResponse<List<NotificationResponse>>> findByNotificationStatus(@PathVariable("notificationStatus") NotificationStatus notificationStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(notificationService.findByNotificationStatus(notificationStatus)));
    }
}
