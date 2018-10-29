package org.snow.service;

import org.snow.model.business.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Boolean addRoom(Room room);

    Boolean updateRoomById(Long roomId, Room room);

    Boolean deleteRoomById(Long roomId);

    List<Room> searchRooms(Room room);
}
