package com.example.ekost.entity;

import com.example.ekost.model.Response.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
 private String orderId;
 private String customerId;
 private String customerName;
 private LocalDateTime transactionDate;
 private List<OrderDetailResponse> orderDetails;
}
