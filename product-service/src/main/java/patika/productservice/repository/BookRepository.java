package patika.productservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import patika.productservice.entity.Book;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("SELECT b FROM Book b WHERE b.id IN :bookIds")
    List<Book> filterByIdList(@Param("bookIds") List<Integer> bookIds);

    @Query("SELECT b FROM Book b WHERE b.publisherId = :publisherId")
    List<Book> filterByPublisherId(@Param("publisherId") Integer publisherId);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId")
    List<Book> filterByAuthorId(@Param("authorId") Integer authorId);

}