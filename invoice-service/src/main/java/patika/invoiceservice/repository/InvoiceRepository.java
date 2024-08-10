package patika.invoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.invoiceservice.entity.Invoice;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
