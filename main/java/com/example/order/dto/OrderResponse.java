package com.example.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private String status;
    private List<Long> reservedFromBatchIds;
    private String message;
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Long> getReservedFromBatchIds() {
		return reservedFromBatchIds;
	}
	public void setReservedFromBatchIds(List<Long> reservedFromBatchIds) {
		this.reservedFromBatchIds = reservedFromBatchIds;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public OrderResponse() {}
	
	public OrderResponse(Long orderId, Long productId, String productName, Integer quantity, String status,
			List<Long> reservedFromBatchIds, String message) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.status = status;
		this.reservedFromBatchIds = reservedFromBatchIds;
		this.message = message;
	}

}