package org.snow.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.snow.model.business.Claxx;
import org.snow.model.business.Room;

import java.util.List;

public interface RoomMapper {
    List<Room> searchRooms(@Param("room") Room room);

}
