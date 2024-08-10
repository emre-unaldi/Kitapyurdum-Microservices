package patika.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import patika.orderservice.entity.Order;
import patika.orderservice.entity.dto.request.OrderSaveRequest;
import patika.orderservice.repository.OrderRepository;
import patika.orderservice.service.OrderService;
import patika.orderservice.utils.ObjectFactory;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 23.06.2024
 */
@WebMvcTest({
        OrderController.class
})
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    private static Order order;
    private static OrderSaveRequest orderSaveRequest;

    @BeforeAll
    static void setUp() {
        order = ObjectFactory.getInstance().getOrder();
        orderSaveRequest = ObjectFactory.getInstance().getOrderSaveRequest();
    }

    @Test
    public void givenOrder_whenSave_thenSuccess() throws Exception {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        mockMvc
                .perform(
                        post("/api/v1/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(orderSaveRequest))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("SUCCESS"));
    }

    @Test
    public void givenOrders_whenFindAll_thenSuccess() throws Exception {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        mockMvc
                .perform(
                        get("/api/v1/orders")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("SUCCESS"));
    }

    @Test
    public void givenExistingOrder_whenFindById_thenSuccess() throws Exception {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        mockMvc
                .perform(
                        get("/api/v1/orders/{orderId}", order.getId())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("SUCCESS"));
    }

    @Test
    public void givenOrders_whenFilterByCustomerId_thenSuccess() throws Exception {
        when(orderRepository.filterByCustomerId(anyInt())).thenReturn(List.of(order));

        mockMvc
                .perform(
                        get("/api/v1/orders/filterByCustomerId/{orderId}", order.getCustomerId())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("SUCCESS"));
    }

}
