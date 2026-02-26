package com.example.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.service.OrderService;

@ExtendWith(MockitoExtension.class) // Initializes mocks and handles strict stubbing
class OrderControllerTest {

    @Mock
    private OrderService orderService; // Creates the mock object

    @InjectMocks
    private OrderController orderController; // Injects the mock into the controller

    @Test
    @DisplayName("Should return ResponseEntity with OrderResponse when placeOrder is called")
    void placeOrder_ReturnsResponse() {
        // 1. Arrange
        OrderRequest request = new OrderRequest();
        OrderResponse mockResponse = new OrderResponse();
        Long orderId = 123L;
        mockResponse.setOrderId(orderId);
        
        when(orderService.placeOrder(any(OrderRequest.class))).thenReturn(mockResponse);

        // 2. Act
        ResponseEntity<OrderResponse> result = orderController.placeOrder(request);

        // 3. Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(123L, result.getBody().getOrderId());
        
        // Verify the service was actually called once
        verify(orderService).placeOrder(request);
    }
}