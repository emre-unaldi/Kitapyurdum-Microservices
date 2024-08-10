package patika.orderservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.entity.dto.response.*;
import patika.orderservice.repository.OrderRepository;
import patika.orderservice.service.Impl.OrderServiceImpl;
import patika.orderservice.utils.ObjectFactory;
import patika.orderservice.utils.client.service.InvoiceService;
import patika.orderservice.utils.client.service.ProductService;
import patika.orderservice.utils.producer.NotificationProducer;
import patika.orderservice.utils.producer.dto.NotificationDto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private static OrderSaveRequest orderSaveRequest;
    private static Order order;
    private static BookResponse bookResponse;
    private static MagazineResponse magazineResponse;
    private static OrderResponse orderResponse;
    private static InvoiceResponse invoiceResponse;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ProductService productService;

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private NotificationProducer notificationProducer;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeAll
    static void setUp() {
        orderSaveRequest = ObjectFactory.getInstance().getOrderSaveRequest();
        order = ObjectFactory.getInstance().getOrder();
        bookResponse = ObjectFactory.getInstance().getBookResponse();
        magazineResponse = ObjectFactory.getInstance().getMagazineResponse();
        orderResponse = ObjectFactory.getInstance().getOrderResponse();
        invoiceResponse = ObjectFactory.getInstance().getInvoiceResponse();
    }

    @Test
    void givenOrderSaveRequest_whenSave_thenOrderResponseShouldBeReturned() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(productService.filterByBookIdList(anyList())).thenReturn(List.of(bookResponse));
        when(productService.filterByMagazineIdList(anyList())).thenReturn(List.of(magazineResponse));
        when(invoiceService.save(anyInt())).thenReturn(invoiceResponse);

        OrderResponse result = orderService.save(orderSaveRequest);
        assertEquals(orderResponse, result);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(productService, times(1)).filterByBookIdList(anyList());
        verify(productService, times(1)).filterByMagazineIdList(anyList());
        verify(invoiceService, times(1)).save(any(Integer.class));
        verify(notificationProducer, times(2)).sendNotification(any(NotificationDto.class));
    }

    @Test
    void givenFindAllOrders_whenFindAll_thenListOfOrderResponsesShouldBeReturned() {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(productService.filterByBookIdList(anyList())).thenReturn(List.of(bookResponse));
        when(productService.filterByMagazineIdList(anyList())).thenReturn(List.of(magazineResponse));

        List<OrderResponse> actualOrders = orderService.findAll();
        assertEquals(List.of(order).size(), actualOrders.size());

        verify(orderRepository, times(1)).findAll();
        verify(productService, times(1)).filterByBookIdList(anyList());
        verify(productService, times(1)).filterByMagazineIdList(anyList());
    }

    @Test
    void givenFindOrderById_whenFindById_thenOrderResponseShouldBeReturned() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(productService.filterByBookIdList(anyList())).thenReturn(List.of(bookResponse));
        when(productService.filterByMagazineIdList(anyList())).thenReturn(List.of(magazineResponse));

        OrderResponse result = orderService.findById(order.getId());
        assertNotNull(result);

        verify(orderRepository, times(1)).findById(order.getId());
        verify(productService, times(1)).filterByBookIdList(anyList());
        verify(productService, times(1)).filterByMagazineIdList(anyList());
    }

    @Test
    void givenFilterOrdersByCustomerId_whenFilterByCustomerId_thenListOfOrderResponsesShouldBeReturned() {
        when(orderRepository.filterByCustomerId(order.getCustomerId())).thenReturn(List.of(order));
        when(productService.filterByBookIdList(anyList())).thenReturn(List.of(bookResponse));
        when(productService.filterByMagazineIdList(anyList())).thenReturn(List.of(magazineResponse));

        List<OrderResponse> result = orderService.filterByCustomerId(order.getCustomerId());
        assertEquals(List.of(order).size(), result.size());

        verify(orderRepository, times(1)).filterByCustomerId(order.getCustomerId());
        verify(productService, times(1)).filterByBookIdList(anyList());
        verify(productService, times(1)).filterByMagazineIdList(anyList());
    }
}
