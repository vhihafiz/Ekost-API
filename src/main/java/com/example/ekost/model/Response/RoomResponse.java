package com.example.ekost.model.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RoomResponse {
    private String roomId;
    private String description;
    private String customerId;
    private String customerName;
}
