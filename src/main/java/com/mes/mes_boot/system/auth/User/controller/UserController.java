package com.mes.mes_boot.system.auth.User.controller;

import com.mes.mes_boot.system.auth.User.dto.RequestUserDto;
import com.mes.mes_boot.system.auth.User.dto.UserDto;
import com.mes.mes_boot.system.auth.User.service.UserService;
import com.mes.mes_boot.util.ApiResponse;
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

/**
 * 유저 인증 관련 컨트롤러
 * 로그인, 회원가입 등 사용자 인증 기능을 처리
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User", description = "유저 관련 API")
public class UserController {
    private final UserService userService;

    /**
     * 유저 로그인
     *
     * @param request 로그인 요청 정보 (사용자 ID, 비밀번호)
     * @return 로그인 결과 및 사용자 정보
     */
    @PostMapping("/login")
    @Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다")
    public ResponseEntity<ApiResponse<UserDto>> userLogin(@Valid @RequestBody RequestUserDto request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", request.getUserId().trim());
        paramMap.put("password", request.getPassword());

        UserDto user = userService.login(paramMap);

        if (user == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.!"));
        }

        // TODO: JWT 토큰 생성 로직 추가 필요
        return ResponseEntity.ok(ApiResponse.success("로그인 성공", user));
    }
}
