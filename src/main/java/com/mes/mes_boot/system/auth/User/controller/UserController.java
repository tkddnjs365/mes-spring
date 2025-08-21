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

import jakarta.servlet.http.HttpServletRequest;

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
     * @param httpRequest HTTP 요청 객체 (클라이언트 IP 획득용)
     * @return 로그인 결과 및 사용자 정보
     */
    @PostMapping("/login")
    @Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다")
    public ResponseEntity<ApiResponse<UserDto>> userLogin(@Valid @RequestBody RequestUserDto request, HttpServletRequest httpRequest) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", request.getUserId().trim());
        paramMap.put("password", request.getPassword());
        paramMap.put("ipAddr", getClientIpAddress(httpRequest)); // 클라이언트 IP 주소 추가

        UserDto user = userService.login(paramMap);

        if (user == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("로그인 실패: 아이디 또는 비밀번호를 확인해주세요.!"));
        }

        // TODO: JWT 토큰 생성 로직 추가 필요
        return ResponseEntity.ok(ApiResponse.success("로그인 성공", user));
    }

    /**
     * 클라이언트의 실제 IP 주소를 획득
     * 프록시 서버나 로드밸런서를 통해 접근하는 경우 실제 클라이언트 IP를 추출
     * 
     * @param request HTTP 요청 객체
     * @return 클라이언트 IP 주소
     */
    private String getClientIpAddress(HttpServletRequest request) {
        // X-Forwarded-For 헤더 확인 (프록시나 로드밸런서를 거친 경우)
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0].trim(); // 첫 번째 IP가 실제 클라이언트 IP
        }
        
        // X-Real-IP 헤더 확인 (nginx 등에서 사용)
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        // 기본적으로 RemoteAddr 반환
        return request.getRemoteAddr();
    }
}
