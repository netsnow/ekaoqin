package org.snow.service.impl;


import org.snow.dao.jpa.RoomRepository;
import org.snow.model.business.Room;
import org.snow.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {

        Iterable<Room> geted = roomRepository.findAll();
        List<Room> list = new ArrayList<Room>();
        geted.forEach(single -> {
            if (single.getIsDeleted() == null || single.getIsDeleted() == false) {
                list.add(single);
            }
        });
        return list;
    }

    @Override
    public Boolean addRoom(Room room) {
        roomRepository.save(room);
        return true;
    }

    @Override
    public Boolean updateRoomById(Long roomId, Room room) {
        room.setId(roomId);
        roomRepository.save(room);
        return true;
    }

    @Override
    public Boolean deleteRoomById(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        room.get().setIsDeleted(true);
        roomRepository.save(room.get());
        return true;
    }
}
