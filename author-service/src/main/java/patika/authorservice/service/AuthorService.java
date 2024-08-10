package patika.authorservice.service;

import patika.authorservice.entity.dto.request.AuthorIdsRequest;
import patika.authorservice.entity.dto.request.AuthorSaveRequest;
import patika.authorservice.entity.dto.response.AuthorResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface AuthorService {
    AuthorResponse save(AuthorSaveRequest request);
    List<AuthorResponse> findAll();
    AuthorResponse findById(Integer authorId);
    List<AuthorResponse> filterByIdList(AuthorIdsRequest request);
}
