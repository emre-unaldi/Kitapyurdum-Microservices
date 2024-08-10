package patika.authorservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.authorservice.entity.Author;
import patika.authorservice.entity.dto.request.AuthorIdsRequest;
import patika.authorservice.entity.dto.request.AuthorSaveRequest;
import patika.authorservice.entity.dto.response.*;
import patika.authorservice.repository.AuthorRepository;
import patika.authorservice.service.AuthorService;
import patika.authorservice.utils.client.service.ProductService;
import patika.authorservice.utils.converter.AuthorConverter;

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
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ProductService productService;

    /**
     * Constructs a new instance of {@code AuthorServiceImpl} using the provided {@link AuthorRepository}.
     *
     * @param authorRepository An instance of {@link AuthorRepository} to interact with the database for author-related operations
     */
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ProductService productService) {
        this.authorRepository = authorRepository;
        this.productService = productService;
    }

    /**
     * Saves an author record and initializes their books and magazines lists. Returns the saved author along with a success message.
     *
     * @param request An {@link AuthorSaveRequest} object containing the author's details
     * @return A {@link AuthorResponse} object representing the saved author
     */
    @Override
    public AuthorResponse save(AuthorSaveRequest request) {
        Author author = AuthorConverter.toAuthor(request);
        authorRepository.save(author);

        return prepareAuthorResponse(author);
    }

    /**
     * Retrieves all authors and converts them to a list of {@link AuthorResponse} objects.
     *
     * @return A list of {@link AuthorResponse} objects representing all authors
     */
    @Override
    public List<AuthorResponse> findAll() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(this::prepareAuthorResponse)
                .toList();
    }

    /**
     * Finds an author by their ID and returns them as an {@link AuthorResponse} object.
     *
     * @param authorId The ID of the author to find
     * @return The found author as an {@link AuthorResponse} object, or null if not found
     */
    @Override
    public AuthorResponse findById(Integer authorId) {
        Optional<Author> foundAuthor = authorRepository.findById(authorId);

        if (foundAuthor.isEmpty()) return null;
        Author author = foundAuthor.get();

        return prepareAuthorResponse(author);
    }

    /**
     * Filters authors by a list of IDs and returns them as a list of {@link AuthorResponse} objects.
     *
     * @param request A list of IDs to filter authors by
     * @return A list of {@link AuthorResponse} objects representing the filtered authors
     */
    @Override
    public List<AuthorResponse> filterByIdList(AuthorIdsRequest request) {
        List<Author> authors = authorRepository.filterByIdList(request.getAuthorIds());

        return authors.stream()
                .map(this::prepareAuthorResponse)
                .toList();
    }

    private AuthorResponse prepareAuthorResponse(Author author) {
        List<BookResponse> books = productService.filterByBookAuthorId(author.getId());
        List<MagazineResponse> magazines = productService.filterByMagazineAuthorId(author.getId());

        return AuthorConverter.toAuthorResponse(author, books, magazines);
    }
}
