package patika.authorservice.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
public class AuthorSaveRequest {
    private String name;
    private String surname;
    private String bio;
}
