package com.example.ekost.controller;


import com.example.ekost.entity.OrderResponse;
import com.example.ekost.model.Request.OrderRequest;
import com.example.ekost.model.Response.CommonResponse;
import com.example.ekost.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new order")
                        .data(orderResponse)
                        .build());
    }

  /*  @GetMapping
    @PreAuthorize("hasRole('ADMIN') and @userSecurity.checkAdmin(authentication, #request.getAdminId())")
    public ResponseEntity<?> getAll() {
        List<OrderResponse> orderResponses = orderService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all transaction")
                        .data(orderResponses)
                        .build());
    }*/


}
