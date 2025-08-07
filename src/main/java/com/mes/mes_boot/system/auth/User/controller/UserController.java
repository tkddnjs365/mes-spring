package com.mes.mes_boot.system.auth.User.controller;

import com.mes.mes_boot.system.auth.User.dto.RequestUserDto;
import com.mes.mes_boot.system.auth.User.dto.ResponseUserDto;
import com.mes.mes_boot.system.auth.User.dto.UserDto;
import com.mes.mes_boot.system.auth.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    /* 유저 로그인 */
    @PostMapping("/login")
    @Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다")
    public ResponseEntity<ResponseUserDto> userLogin(@Valid @RequestBody RequestUserDto request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", request.getUserId().trim());
        paramMap.put("password", request.getPassword());

        UserDto User = userService.login(paramMap);

        if (User == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        // 로그인 성공 응답
        ResponseUserDto response = ResponseUserDto.builder()
                .success(true)
                .message("로그인 성공")
                .accessToken("") // JWT 토큰 생성 로직 추가 필요
                .refreshToken("") // Refresh 토큰 생성 로직 추가 필요
                .user(User)
                .build();

        return ResponseEntity.ok(response);
    }
}
