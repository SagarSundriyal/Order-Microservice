package com.example.order.serviceimpl;

import com.example.order.client.InventoryClient;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    
    public OrderServiceImpl(OrderRepository orderRepository, InventoryClient inventoryClient) {
		this.orderRepository = orderRepository;
		this.inventoryClient = inventoryClient;
	}

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        
        List<Long> batchIds = inventoryClient.updateInventory(request.getProductId(), request.getQuantity());
       // System.out.println(" batchIds:- "+batchIds);
        
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        order.setProductName("Product-" + request.getProductId()); 
        // System.out.println(" order:- "+order);
        
        Order savedOrder = orderRepository.save(order);
       // System.out.println(" savedOrder:- "+savedOrder);
        
        return new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getProductId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getStatus(),
                batchIds,
                "Order placed. Inventory reserved."
        );
    }
    
}