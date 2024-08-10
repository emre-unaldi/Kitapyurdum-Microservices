package patika.invoiceservice.service;

import patika.invoiceservice.entity.dto.request.InvoiceSaveRequest;
import patika.invoiceservice.entity.dto.response.InvoiceResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface InvoiceService {
    InvoiceResponse save(InvoiceSaveRequest request);
    List<InvoiceResponse> findAll();
    InvoiceResponse findById(Integer invoiceId);
}
