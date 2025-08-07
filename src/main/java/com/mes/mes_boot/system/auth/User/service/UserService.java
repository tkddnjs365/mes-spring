package com.mes.mes_boot.system.auth.User.service;

import com.mes.mes_boot.system.auth.User.dto.UserDto;
import com.mes.mes_boot.system.auth.User.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto login(Map<String, Object> paramMap) {
        return userMapper.userLogin(paramMap);
    }
}
