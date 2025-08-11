package com.mes.mes_boot.system.auth.SuperUser.controller;

import com.mes.mes_boot.system.auth.SuperUser.dto.company.CompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.ResponseCompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.RequestSuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.ResponseSuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.SuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.service.SuperUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j // 로깅
@RestController // REST API 컨트롤러임을 Spring에 알림
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성 (Loombok)
@RequestMapping("/api/superUser") // 컨트롤러의 기본 URL 경로 설정 ( http://localhost:port/api/superUser )
@Tag(name = "SuperUser", description = "슈퍼유저 관련 API")
public class SuperUserController {
    private final SuperUserService superUserService; // 서비스 계층 의존성 주입

    /* 슈퍼유저 로그인 */
    @PostMapping("/login") // POST 요청을 http://localhost:port/api/superUser/login 경로로 매핑
    @Operation(summary = "슈퍼유저 로그인", description = "슈퍼유저 로그인을 처리합니다")
    public ResponseEntity<ResponseSuperUserDto> userLogin(@Valid @RequestBody RequestSuperUserDto request) {
        // @Valid: 요청 데이터 검증, @RequestBody: JSON을 객체로 변환
        log.info("로그인 시도: userId={}", request.getUserId());

        SuperUserDto User = superUserService.login(request); // 서비스 계층의 login 메서드 호출

        if (User == null) {
            return ResponseEntity.status(401).build(); // HTTP 401 응답 반환
        }

        // 로그인 성공 응답
        ResponseSuperUserDto response = ResponseSuperUserDto.builder()
                .success(true) // 성공 플래그
                .message("로그인 성공") // 성공 메시지
                .accessToken("") // JWT 토큰 생성 로직 추가 필요
                .refreshToken("") // Refresh 토큰 생성 로직 추가 필요
                .User(User) // 사용자 정보
                .build();

        return ResponseEntity.ok(response); // HTTP 200 과 함께 응답 반환
    }

    /* 회사코드 생성 */
    @PostMapping("/createCompany")
    @Operation(summary = "회사 생성", description = "새로운 회사를 생성 합니다.")
    public ResponseEntity<Map<String, Object>> createCompany(@Valid @RequestBody RequestCreateCompanyDto request) {
        try {
            boolean result = superUserService.createCompany(request);

            Map<String, Object> response = new HashMap<>();
            if (result) {
                response.put("success", true);
                response.put("message", "회사가 성공적으로 생성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "회사 생성에 실패했습니다.");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "서버 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    /* 회사별 관리자 조회 */
    @GetMapping("/getCompanyAdmin/{company_code}")
    @Operation(summary = "회사별 관리자 생성", description = "회사별 관리자를 추가 합니다.")
    public ResponseEntity<ResponseCompanyAdminDto> getComapnyAdmin(@PathVariable String company_code) {
        try {
            List<CompanyAdminDto> re_dto = superUserService.getComapnyAdmin(company_code);

            if (re_dto == null) {
                return ResponseEntity.status(500).body(
                        ResponseCompanyAdminDto.builder()
                                .success(false)
                                .message("서버 오류")
                                .companyAdminDtoList(null)
                                .build()
                );
            }

            ResponseCompanyAdminDto response = ResponseCompanyAdminDto.builder()
                    .success(true)
                    .message("성공")
                    .companyAdminDtoList(re_dto)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    ResponseCompanyAdminDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .companyAdminDtoList(null)
                            .build()
            );
        }
    }

    /* 회사별 관리자 생성 */
    @PostMapping("/createCompanyAdmin")
    @Operation(summary = "회사별 관리자 생성", description = "회사별 관리자를 추가 합니다.")
    public ResponseEntity<Map<String, Object>> createCompanyAdmin(@Valid @RequestBody RequestCreateCompanyAdminDto request) {
        try {
            boolean result = superUserService.createCompanyAdmin(request);

            Map<String, Object> response = new HashMap<>();
            if (result) {
                response.put("success", true);
                response.put("message", "성공적으로 생성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "생성에 실패했습니다.");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "서버 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    /* 회사별 관리자 삭제 */
    @DeleteMapping("/deleteCompanyAdmin/{adminId}")
    @Operation(summary = "회사별 관리자 삭제", description = "회사별 관리자를 삭제 합니다.")
    public ResponseEntity<Map<String, Object>> deleteCompanyAdmin(@PathVariable String adminId) {
        try {
            boolean result = superUserService.deleteCompanyAdmin(adminId);

            Map<String, Object> response = new HashMap<>();
            if (result) {
                response.put("success", true);
                response.put("message", "성공");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "실패");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "서버 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
