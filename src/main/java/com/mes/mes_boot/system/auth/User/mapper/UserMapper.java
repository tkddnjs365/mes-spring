package com.mes.mes_boot.system.auth.User.mapper;

import com.mes.mes_boot.system.auth.User.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    UserDto userLogin(Map<String, Object> paramMap);
}
