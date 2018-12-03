package org.snow.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.snow.model.security.User;

import java.util.List;

public interface UserMapper {
    List<User> searchUsers(@Param("user") User user);

    List<User> searchFuzzyUsers(@Param("searchKey") String searchKey);

}
