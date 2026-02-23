package com.example.order.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.example.order.dto.UpdateInventoryRequest;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Component
@Slf4j
public class InventoryClient {
	
    private final RestClient restClient;

    public InventoryClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081/inventory") 
                .build();
    }

    public List<Long> updateInventory(Long productId, Integer quantity) {  
        try {
            return restClient.post()
                    .uri("/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateInventoryRequest(productId, quantity))
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<List<Long>>() {})
                    .getBody();
        } catch (Exception e) {
           // logger.error("Error calling Inventory Service for ProductId {}: {}", productId, e.getMessage());
            throw e; 
        }
    }
    
}