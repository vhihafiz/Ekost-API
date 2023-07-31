package com.example.ekost.service;

import com.example.ekost.entity.Customer;
import com.example.ekost.entity.Room;
import com.example.ekost.model.Request.RoomRequest;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.model.Response.RoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomService {
    Room createRoom(Room room);
    List<Room> getAll();
    Room getById(String id);
    RoomResponse updateRoom(RoomRequest request);
    void deleteRoom(String id);
}
