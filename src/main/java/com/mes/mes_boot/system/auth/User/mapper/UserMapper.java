package com.mes.mes_boot.system.auth.User.mapper;

import com.mes.mes_boot.system.auth.User.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    UserDto userLogin(Map<String, Object> paramMap);
    
    /**
     * 로그인 히스토리 저장
     * @param paramMap 히스토리 파라미터 (ipAddr, userIdx)
     */
    void insertLoginHistory(Map<String, Object> paramMap);
}
