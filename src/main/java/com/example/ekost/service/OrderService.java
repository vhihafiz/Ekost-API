package com.example.ekost.service;

import com.example.ekost.entity.OrderResponse;
import com.example.ekost.model.Request.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder (OrderRequest request);

    List<OrderResponse> getAll();

}
