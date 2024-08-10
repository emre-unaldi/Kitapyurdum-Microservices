package patika.publisherservice.service;

import patika.publisherservice.entity.dto.request.PublisherSaveRequest;
import patika.publisherservice.entity.dto.response.PublisherProductResponse;
import patika.publisherservice.entity.dto.response.PublisherResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface PublisherService {
    PublisherResponse save(PublisherSaveRequest request);
    List<PublisherResponse> findAll();
    PublisherResponse findById(Integer publisherId);
    PublisherProductResponse filterProductsById(Integer publisherId);
    PublisherProductResponse filterProductsByName(String name);
}
