package patika.authorservice.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
public class AuthorResponse {
    private Integer id;
    private String name;
    private String surname;
    private String bio;
    private List<AuthorBookResponse> books;
    private List<AuthorMagazineResponse> magazines;
}
