package com.example.order.service;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;

public interface OrderService {
	
    /**
     * Places an order and communicates with Inventory Service 
     * to reserve stock from batches.
     */
    OrderResponse placeOrder(OrderRequest request);
}