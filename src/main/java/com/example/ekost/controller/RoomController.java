package com.example.ekost.controller;


import com.example.ekost.entity.Room;
import com.example.ekost.model.Request.RoomRequest;
import com.example.ekost.model.Response.CommonResponse;
import com.example.ekost.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createNewRoom(@RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<Room>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new room")
                        .data(roomService.createRoom(room))
                        .build());
    }


    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<Room> rooms = roomService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all room")
                        .data(rooms)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get room by id")
                        .data(roomService.getById(id))
                        .build());
    }

    @PutMapping
    public ResponseEntity<String> updateRoom(@RequestBody RoomRequest request) {
        roomService.updateRoom(request);
        return ResponseEntity.ok("Room updated successfully.");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete room")
                        .build());
    }
}
