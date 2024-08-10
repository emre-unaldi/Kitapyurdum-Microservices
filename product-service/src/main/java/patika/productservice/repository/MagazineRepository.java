package patika.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import patika.productservice.entity.Magazine;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Integer>, JpaSpecificationExecutor<Magazine> {

    @Query("SELECT m FROM Magazine m WHERE m.id IN :magazineIds ")
    List<Magazine> filterByIdList(@Param("magazineIds") List<Integer> magazineIds);

    @Query("SELECT m FROM Magazine m WHERE m.publisherId = :publisherId")
    List<Magazine> filterByPublisherId(@Param("publisherId") Integer publisherId);

    @Query("SELECT m FROM Magazine m WHERE :authorId MEMBER OF m.authorIds")
    List<Magazine> filterByAuthorId(@Param("authorId") Integer authorId);

}