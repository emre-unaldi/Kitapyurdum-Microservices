package patika.publisherservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.publisherservice.entity.Publisher;
import patika.publisherservice.entity.dto.request.PublisherSaveRequest;
import patika.publisherservice.entity.dto.response.*;
import patika.publisherservice.repository.PublisherRepository;
import patika.publisherservice.service.PublisherService;
import patika.publisherservice.utils.client.service.ProductService;
import patika.publisherservice.utils.converter.PublisherConverter;

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
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ProductService productService;

    /**
     * Constructs a new instance of {@code PublisherServiceImpl} using the provided {@link PublisherRepository}.
     *
     * @param publisherRepository An instance of {@link PublisherRepository} to interact with the database for publisher-related operations
     */
    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, ProductService productService) {
        this.publisherRepository = publisherRepository;
        this.productService = productService;
    }

    /**
     * Saves a publisher record, initializes their books and magazines lists, and saves the publisher. Returns the saved publisher along with a success message.
     *
     * @param request A {@link PublisherSaveRequest} object containing the publisher's details
     * @return A {@link PublisherResponse} object representing the saved publisher
     */
    @Override
    public PublisherResponse save(PublisherSaveRequest request) {
        Publisher publisher = PublisherConverter.toPublisher(request);
        publisherRepository.save(publisher);

        return preparePublisherResponse(publisher);
    }

    /**
     * Retrieves all publishers and converts them to a list of {@link PublisherResponse} objects.
     *
     * @return A list of {@link PublisherResponse} objects representing all publishers
     */
    @Override
    public List<PublisherResponse> findAll() {
        List<Publisher> publishers = publisherRepository.findAll();

        return publishers.stream()
                .map(this::preparePublisherResponse)
                .toList();
    }

    /**
     * Finds a publisher by their ID and returns them as a {@link PublisherResponse} object.
     *
     * @param publisherId The ID of the publisher to find
     * @return The found publisher as a {@link PublisherResponse} object, or null if not found
     */
    @Override
    public PublisherResponse findById(Integer publisherId) {
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisherId);

        if (foundPublisher.isEmpty()) return null;
        Publisher publisher = foundPublisher.get();

        return preparePublisherResponse(publisher);
    }

    /**
     * Filters products by publisher ID and returns them as a {@link PublisherProductResponse} object.
     *
     * @param publisherId The ID of the publisher to filter products by
     * @return A {@link PublisherProductResponse} object representing the filtered products, or null if not found
     */
    @Override
    public PublisherProductResponse filterProductsById(Integer publisherId) {
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisherId);

        if (foundPublisher.isEmpty()) return null;
        Publisher publisher = foundPublisher.get();

        return preparePublisherProductResponse(publisher);
    }

    /**
     * Filters products by publisher name and returns them as a {@link PublisherProductResponse} object.
     *
     * @param name The name of the publisher to filter products by
     * @return A {@link PublisherProductResponse} object representing the filtered products, or null if not found
     */
    @Override
    public PublisherProductResponse filterProductsByName(String name) {
        Optional<Publisher> foundPublisher = publisherRepository.findByName(name);

        if (foundPublisher.isEmpty()) return null;
        Publisher publisher = foundPublisher.get();

        return preparePublisherProductResponse(publisher);
    }

    /**
     * Prepares a {@link PublisherResponse} object from a given {@link Publisher} entity, including associated books and magazines.
     *
     * @param publisher The {@link Publisher} entity to convert
     * @return A {@link PublisherResponse} object representing the publisher
     */
    private PublisherResponse preparePublisherResponse(Publisher publisher) {
        List<BookResponse> books = productService.filterByBookPublisherId(publisher.getId());
        List<MagazineResponse> magazines = productService.filterByMagazinePublisherId(publisher.getId());

        return PublisherConverter.toPublisherResponse(publisher, books, magazines);
    }

    /**
     * Prepares a {@link PublisherProductResponse} object from a given {@link Publisher} entity, including associated books and magazines.
     *
     * @param publisher The {@link Publisher} entity to convert
     * @return A {@link PublisherProductResponse} object representing the publisher's products
     */
    private PublisherProductResponse preparePublisherProductResponse(Publisher publisher) {
        List<BookResponse> books = productService.filterByBookPublisherId(publisher.getId());
        List<MagazineResponse> magazines = productService.filterByMagazinePublisherId(publisher.getId());

        return PublisherConverter.toPublisherProductResponse(books, magazines);
    }
}
