package com.example.ekost.service.impl;

import com.example.ekost.entity.Customer;
import com.example.ekost.entity.Room;
import com.example.ekost.model.Request.RoomRequest;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.model.Response.RoomResponse;
import com.example.ekost.repository.RoomRepository;
import com.example.ekost.service.CustomerService;
import com.example.ekost.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final CustomerService customerService;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    @Override
    public Room getById(String id) {
        Optional<Room> room = roomRepository.findBy(id);
        if(room.isPresent()){
            return room.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id room not found");
        }
    }

    @Override
    public RoomResponse updateRoom(RoomRequest request) {
        Room roomFound = getById(request.getRoomId());

        roomFound.setId(request.getRoomId());
        roomFound.setDescription(request.getDescription());

        RoomResponse update = RoomResponse.builder()
                .roomId(roomFound.getId())
                .description(request.getDescription())
                .build();
        roomRepository.save(roomFound);
        return update;
    }

    @Override
    public void deleteRoom(String id) {
        Room roomFound = getById(id);
        roomRepository.deleteRoom(roomFound.getId());
    }
}
