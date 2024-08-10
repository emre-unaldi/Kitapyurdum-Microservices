package patika.orderservice.service;

import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.request.OrderSearchRequest;
import patika.orderservice.entity.dto.response.OrderResponse;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
public interface OrderService {
    OrderResponse save(OrderSaveRequest request);
    List<OrderResponse> findAll();
    OrderResponse findById(Integer orderId);
    List<OrderResponse> filterByCustomerId(Integer customerId);
    List<OrderResponse> search(OrderSearchRequest request);
}
