package com.example.ekost.model.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RoomRequest {
    private String roomId;
    private String description;
}
