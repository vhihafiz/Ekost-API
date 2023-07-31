package com.example.ekost.model.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class CustomerRequest {
    private String name;
    private String email;
    private String address;
}
