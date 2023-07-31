package com.example.ekost.service.impl;

import com.example.ekost.entity.*;
import com.example.ekost.model.Request.OrderRequest;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.model.Response.OrderDetailResponse;
import com.example.ekost.model.Response.RoomResponse;
import com.example.ekost.repository.OrderRepository;
import com.example.ekost.service.CustomerService;
import com.example.ekost.service.OrderService;
import com.example.ekost.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Customer customerFound = customerService.getById(request.getCustomerId());

        List<OrderDetail> orderDetails =request.getOrderDetailRequests().stream().map(orderDetailRequest -> {
            Room roomFound = roomService.getById(orderDetailRequest.getRoomId());

            return OrderDetail.builder()
                    .room(roomFound)
                    .build();
        }).collect(Collectors.toList());


        Order order = Order.builder()
                .customer(customerFound)
                .transDate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {
            orderDetail.setOrder(order);

            Room roomFound = roomService.getById(orderDetail.getRoom().getId());
            RoomResponse roomResponse = RoomResponse.builder()
                    .roomId(orderDetail.getRoom().getId())
                    .description(roomFound.getDescription())
                    .customerId(orderDetail.getOrder().getCustomer().getId())
                    .customerName(orderDetail.getOrder().getCustomer().getName())
                    .build();
            roomFound.setCustomer(customerFound);

            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .roomResponse(roomResponse)
                    .build();
        }).collect(Collectors.toList());

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(customerFound.getName())
                .email(customerFound.getEmail())
                .address(customerFound.getAddress())
                .build();

       return OrderResponse.builder()
                .orderId(order.getId())
                .customerId(customerFound.getId())
                .customerName(customerResponse.getName())
                .transactionDate(order.getTransDate())
               .orderDetails(orderDetailResponses)
                .build();

    }

    @Override
    public List<OrderResponse> getAll() {
        return null;
    }
}
