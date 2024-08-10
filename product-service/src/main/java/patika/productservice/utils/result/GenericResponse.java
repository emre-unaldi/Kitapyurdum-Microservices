package patika.productservice.utils.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import patika.productservice.utils.constants.ProductConstants;

import java.time.LocalDateTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private String message;
    private LocalDateTime date;
    private HttpStatus httpStatus;
    private T data;

    public static GenericResponse<ExceptionResponse> failed(String message) {
        return GenericResponse.<ExceptionResponse>builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(message)
                .data(new ExceptionResponse(message))
                .date(LocalDateTime.now())
                .build();
    }

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .message(ProductConstants.SUCCESS)
                .date(LocalDateTime.now())
                .httpStatus(HttpStatus.OK)
                .data(data)
                .build();
    }
}
