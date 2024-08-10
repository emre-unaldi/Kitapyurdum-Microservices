package patika.productservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.productservice.entity.Magazine;
import patika.productservice.entity.dto.request.MagazineIdsRequest;
import patika.productservice.entity.dto.request.MagazineSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.*;
import patika.productservice.repository.MagazineRepository;
import patika.productservice.repository.spesification.ProductSpecification;
import patika.productservice.service.MagazineService;
import patika.productservice.utils.converter.MagazineConverter;

import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Service
public class MagazineServiceImpl implements MagazineService {
    private final MagazineRepository magazineRepository;


    /**
     * Constructs a new instance of {@code MagazineServiceImpl} using the provided {@link MagazineRepository}.
     *
     * @param magazineRepository An instance of {@link MagazineRepository} to interact with the database for magazine-related operations
     */
    @Autowired
    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    /**
     * Saves a magazine record, associates it with publishers and authors, and adds it to both entities' publications list. Returns the saved magazine along with a success message.
     *
     * @param request A {@link MagazineSaveRequest} object containing the magazine's details
     * @return A {@link MagazineResponse} object representing the saved magazine
     */
    @Override
    public MagazineResponse save(MagazineSaveRequest request) {
        Magazine magazine = MagazineConverter.toMagazine(request);
        magazineRepository.save(magazine);

        return MagazineConverter.toMagazineResponse(magazine);
    }

    /**
     * Retrieves all magazines and converts them to a list of {@link MagazineResponse} objects.
     *
     * @return A list of {@link MagazineResponse} objects representing all magazines
     */
    @Override
    public List<MagazineResponse> findAll() {
        List<Magazine> magazines = magazineRepository.findAll();

        return MagazineConverter.toListMagazineResponse(magazines);
    }

    /**
     * Finds a magazine by its ID and returns it as a {@link MagazineResponse} object.
     *
     * @param magazineId The ID of the magazine to find
     * @return The found magazine as a {@link MagazineResponse} object, or null if not found
     */
    @Override
    public MagazineResponse findById(Integer magazineId) {
        Optional<Magazine> foundMagazine = magazineRepository.findById(magazineId);

        if (foundMagazine.isEmpty()) return null;
        Magazine magazine = foundMagazine.get();

        return MagazineConverter.toMagazineResponse(magazine);
    }

    /**
     * Filters magazines by their IDs and returns a list of {@link MagazineResponse} objects that match the given IDs.
     *
     * @param request A {@link MagazineIdsRequest} A list of magazine IDs to filter by
     * @return A list of {@link MagazineResponse} objects representing the filtered magazines
     */
    @Override
    public List<MagazineResponse> filterByIdList(MagazineIdsRequest request) {
        List<Magazine> magazines = magazineRepository.filterByIdList(request.getMagazineIds());

        return MagazineConverter.toListMagazineResponse(magazines);
    }

    /**
     * Filters magazines by their associated publisher's ID and returns a list of {@link MagazineResponse} objects that match the given publisher ID.
     *
     * @param publisherId The ID of the publisher whose magazines should be returned
     * @return A list of {@link MagazineResponse} objects representing the magazines published by the specified publisher
     */
    @Override
    public List<MagazineResponse> filterByPublisherId(Integer publisherId) {
        List<Magazine> magazines = magazineRepository.filterByPublisherId(publisherId);

        return MagazineConverter.toListMagazineResponse(magazines);
    }

    /**
     * Filters magazines by their associated author's ID and returns a list of {@link MagazineResponse} objects that match the given author ID.
     *
     * @param authorId The ID of the author whose magazines should be returned
     * @return A list of {@link MagazineResponse} objects representing the magazines written by the specified author
     */
    @Override
    public List<MagazineResponse> filterByAuthorId(Integer authorId) {
        List<Magazine> magazines = magazineRepository.filterByAuthorId(authorId);

        return MagazineConverter.toListMagazineResponse(magazines);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MagazineResponse> search(ProductSearchRequest request) {
        Specification<Magazine> magazineSpecification = ProductSpecification.initMagazineSpecification(request);
        PageRequest pageRequest = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by(Sort.Direction.ASC, "amount")
        );

        Page<Magazine> magazines = magazineRepository.findAll(magazineSpecification, pageRequest);

        return MagazineConverter.toListMagazineResponse(magazines.stream().toList());
    }
}
