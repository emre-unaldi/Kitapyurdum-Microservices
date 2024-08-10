package patika.customerservice.utils.exception;

import patika.customerservice.utils.result.GenericResponse;
import patika.customerservice.utils.result.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(KitapYurdumException.class)
    public GenericResponse<ExceptionResponse> handleException(KitapYurdumException exception) {
        log.error(exception.getLocalizedMessage());

        return GenericResponse.failed(exception.getMessage());
    }
}
