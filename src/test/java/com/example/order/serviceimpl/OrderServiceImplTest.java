package com.example.order.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.order.client.InventoryClient;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequest request;

    @BeforeEach
    void setUp() {
        request = new OrderRequest();
        request.setProductId(101L);
        request.setQuantity(2);
    }

    @Test
    @DisplayName("Should successfully place an order and return response")
    void shouldPlaceOrderSuccessfully() {
        // 1. Arrange
        List<Long> mockBatchIds = Arrays.asList(5001L, 5002L);
        
        Order savedOrder = new Order();
        savedOrder.setOrderId(1L);
        savedOrder.setProductId(101L);
        savedOrder.setProductName("Product-101");
        savedOrder.setQuantity(2);
        savedOrder.setStatus("PLACED");

        // Mocking external calls
        when(inventoryClient.updateInventory(101L, 2)).thenReturn(mockBatchIds);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // 2. Act
        OrderResponse response = orderService.placeOrder(request);

        // 3. Assert
        assertNotNull(response);
        assertEquals(1L, response.getOrderId());
        assertEquals("PLACED", response.getStatus());
        assertEquals("Product-101", response.getProductName());

        // Verify interactions
        verify(inventoryClient, times(1)).updateInventory(101L, 2);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should propagate exception when inventory client fails")
    void shouldThrowExceptionWhenInventoryFails() {
        // Arrange
        when(inventoryClient.updateInventory(anyLong(), anyInt()))
            .thenThrow(new RuntimeException("Inventory Service Down"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(request);
        });

        // Verify repository was never called because of the exception
        verify(orderRepository, never()).save(any(Order.class));
    }
}