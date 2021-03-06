package org.snow.controller;

import org.snow.model.business.Claxx;
import org.snow.model.business.Room;
import org.snow.service.ClaxxService;
import org.snow.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomRestController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(path = "/room", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @RequestMapping(path = "/room", method = RequestMethod.POST)
    public Boolean addRoom(
        @RequestBody Room room
    ) {
        return roomService.addRoom(room);
    }

    @RequestMapping(path = "/room/{id}", method = RequestMethod.PUT)
    public Boolean updateRoomById(
        @PathVariable Long id,
        @RequestBody Room room
    ) {
        return roomService.updateRoomById(id,room);
    }

    @RequestMapping(path = "/room/{id}", method = RequestMethod.DELETE)
    public Boolean deleteRoomById(
        @PathVariable Long id
    ) {
        return roomService.deleteRoomById(id);
    }
    @RequestMapping(path = "/room/search", method = RequestMethod.POST)
    public List<Room> searchRooms(
        @RequestBody Room room
    ) {
        return roomService.searchRooms(room);
    }

}
