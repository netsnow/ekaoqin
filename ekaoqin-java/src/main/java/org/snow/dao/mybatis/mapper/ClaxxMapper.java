package org.snow.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.snow.model.business.Claxx;
import org.snow.model.security.User;

import java.util.List;

public interface ClaxxMapper {
    List<Claxx> searchClaxxes(@Param("claxx") Claxx claxx);

}
