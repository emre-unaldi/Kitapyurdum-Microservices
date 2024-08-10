package patika.productservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patika.productservice.entity.Book;
import patika.productservice.entity.dto.request.BookIdsRequest;
import patika.productservice.entity.dto.request.BookSaveRequest;
import patika.productservice.entity.dto.request.ProductSearchRequest;
import patika.productservice.entity.dto.response.BookResponse;
import patika.productservice.repository.BookRepository;
import patika.productservice.repository.spesification.ProductSpecification;
import patika.productservice.service.BookService;
import patika.productservice.utils.converter.BookConverter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    /**
     * Constructs a new instance of {@code BookServiceImpl} using the provided {@link BookRepository}.
     *
     * @param bookRepository An instance of {@link BookRepository} to interact with the database for book-related operations
     */
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Saves a book record, associates it with an author and a publisher, and adds it to both entities' books list. Returns the saved book along with a success message.
     *
     * @param request A {@link BookSaveRequest} object containing the book's details
     * @return A {@link BookResponse} object representing the saved book
     */
    @Override
    public BookResponse save(BookSaveRequest request) {
        Book book = BookConverter.toBook(request);
        bookRepository.save(book);

        return BookConverter.toBookResponse(book);
    }

    /**
     * Retrieves all books and converts them to a list of {@link BookResponse} objects.
     *
     * @return A list of {@link BookResponse} objects representing all books
     */
    @Override
    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findAll();

        return BookConverter.toListBookResponse(books);
    }

    /**
     * Finds a book by its ID and returns it as a {@link BookResponse} object.
     *
     * @param bookId The ID of the book to find
     * @return The found book as a {@link BookResponse} object, or null if not found
     */
    @Override
    public BookResponse findById(Integer bookId) {
        Optional<Book> foundBook = bookRepository.findById(bookId);

        if (foundBook.isEmpty()) return null;
        Book book = foundBook.get();

        return BookConverter.toBookResponse(book);
    }

    /**
     * Filters books by their IDs and returns a list of {@link BookResponse} objects that match the given IDs.
     *
     * @param request A list of book IDs to filter by
     * @return A list of {@link BookResponse} objects representing the filtered books
     */
    @Override
    public List<BookResponse> filterByIdList(BookIdsRequest request) {
        List<Book> books = bookRepository.filterByIdList(request.getBookIds());

        return BookConverter.toListBookResponse(books);
    }

    /**
     * Filters books by their associated publisher's ID and returns a list of {@link BookResponse} objects that match the given publisher ID.
     *
     * @param publisherId The ID of the publisher whose books should be returned
     * @return A list of {@link BookResponse} objects representing the books published by the specified publisher
     */
    @Override
    public List<BookResponse> filterByPublisherId(Integer publisherId) {
        List<Book> books = bookRepository.filterByPublisherId(publisherId);

        return BookConverter.toListBookResponse(books);
    }

    /**
     * Filters books by their associated author's ID and returns a list of {@link BookResponse} objects that match the given author ID.
     *
     * @param authorId The ID of the author whose books should be returned
     * @return A list of {@link BookResponse} objects representing the books written by the specified author
     */
    @Override
    public List<BookResponse> filterByAuthorId(Integer authorId) {
        List<Book> books = bookRepository.filterByAuthorId(authorId);

        return BookConverter.toListBookResponse(books);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResponse> search(ProductSearchRequest request) {
        Specification<Book> bookSpecification = ProductSpecification.initBookSpecification(request);
        PageRequest pageRequest = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by(Sort.Direction.ASC, "amount")
        );

        Page<Book> books = bookRepository.findAll(bookSpecification, pageRequest);

        return BookConverter.toListBookResponse(books.stream().toList());
    }
}
