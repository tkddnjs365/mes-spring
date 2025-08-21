package com.mes.mes_boot.system.auth.User.service;

import com.mes.mes_boot.system.auth.User.dto.UserDto;
import com.mes.mes_boot.system.auth.User.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 사용자 로그인 처리
     * 로그인 성공 시 user_login_hist 테이블에 로그인 기록을 저장
     * 
     * @param paramMap 로그인 파라미터 (userId, password, ipAddr)
     * @return 로그인한 사용자 정보, 실패 시 null
     */
    public UserDto login(Map<String, Object> paramMap) {
        // 사용자 인증
        UserDto user = userMapper.userLogin(paramMap);
        
        // 로그인 성공 시 로그인 히스토리 저장
        if (user != null) {
            Map<String, Object> historyParam = new HashMap<>();
            historyParam.put("ipAddr", paramMap.get("ipAddr")); // 클라이언트 IP
            historyParam.put("userIdx", user.getId()); // users 테이블의 id
            userMapper.insertLoginHistory(historyParam);
        }
        
        return user;
    }
}
