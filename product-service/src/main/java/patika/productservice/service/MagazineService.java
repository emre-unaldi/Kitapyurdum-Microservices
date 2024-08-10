package patika.productservice.service;

import patika.productservice.entity.dto.request.MagazineIdsRequest;
import patika.productservice.entity.dto.request.MagazineSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.MagazineResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface MagazineService {
    MagazineResponse save(MagazineSaveRequest request);
    List<MagazineResponse> findAll();
    MagazineResponse findById(Integer magazineId);
    List<MagazineResponse> filterByIdList(MagazineIdsRequest request);
    List<MagazineResponse> filterByPublisherId(Integer publisherId);
    List<MagazineResponse> filterByAuthorId(Integer authorId);
    List<MagazineResponse> search(ProductSearchRequest request);
}
