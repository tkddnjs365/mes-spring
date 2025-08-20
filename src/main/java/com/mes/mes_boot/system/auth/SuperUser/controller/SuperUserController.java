package com.mes.mes_boot.system.auth.SuperUser.controller;

import com.mes.mes_boot.system.auth.SuperUser.dto.company.CompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.RequestSuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.SuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.service.SuperUserService;
import com.mes.mes_boot.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 슈퍼유저 관리 컨트롤러
 * 시스템 전체 관리자 기능을 제공하며, 회사 및 회사 관리자 추가/삭제 기능을 처리
 */

@Slf4j // 로깅
@RestController // REST API 컨트롤러임을 Spring에 알림
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성 (Loombok)
@RequestMapping("/api/superUser") // 컨트롤러의 기본 URL 경로 설정 ( http://localhost:port/api/superUser )
@Tag(name = "SuperUser", description = "슈퍼유저 관련 API")
public class SuperUserController {
    private final SuperUserService superUserService; // 서비스 계층 의존성 주입

    /**
     * 슈퍼유저 로그인
     *
     * @param request 로그인 요청 정보
     * @return 로그인 결과 및 사용자 정보
     */
    @PostMapping("/login")
    @Operation(summary = "슈퍼유저 로그인", description = "슈퍼유저 로그인을 처리합니다")
    public ResponseEntity<ApiResponse<SuperUserDto>> userLogin(@Valid @RequestBody RequestSuperUserDto request) {
        log.info("로그인 시도: userId={}", request.getUserId());

        SuperUserDto user = superUserService.login(request);

        if (user == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("로그인 실패: 아이디 또는 비밀번호를 확인해주세요."));
        }

        // TODO: JWT 토큰 생성 로직 추가 필요
        return ResponseEntity.ok(ApiResponse.success("로그인 성공", user));
    }

    /**
     * 회사 생성
     *
     * @param request 생성할 회사 정보
     * @return 회사 생성 결과
     */
    @PostMapping("/createCompany")
    @Operation(summary = "회사 생성", description = "새로운 회사를 생성 합니다.")
    public ResponseEntity<ApiResponse<Boolean>> createCompany(@Valid @RequestBody RequestCreateCompanyDto request) {
        boolean result = superUserService.createCompany(request);
        return ResponseEntity.ok(ApiResponse.success("회사가 성공적으로 생성되었습니다.", result));
    }

    /**
     * 회사별 관리자 조회
     *
     * @param company_code 회사 코드
     * @return 회사별 관리자 목록
     */
    @GetMapping("/getCompanyAdmin/{company_code}")
    @Operation(summary = "회사별 관리자 조회", description = "회사별 관리자 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<List<CompanyAdminDto>>> getComapnyAdmin(@PathVariable String company_code) {
        List<CompanyAdminDto> result = superUserService.getComapnyAdmin(company_code);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 회사별 관리자 생성
     *
     * @param request 생성할 관리자 정보
     * @return 관리자 생성 결과
     */
    @PostMapping("/createCompanyAdmin")
    @Operation(summary = "회사별 관리자 생성", description = "회사별 관리자를 추가 합니다.")
    public ResponseEntity<ApiResponse<Boolean>> createCompanyAdmin(@Valid @RequestBody RequestCreateCompanyAdminDto request) {
        boolean result = superUserService.createCompanyAdmin(request);
        return ResponseEntity.ok(ApiResponse.success("관리자가 성공적으로 생성되었습니다.", result));
    }

    /**
     * 회사별 관리자 삭제
     *
     * @param adminId 삭제할 관리자 ID
     * @return 관리자 삭제 결과
     */
    @DeleteMapping("/deleteCompanyAdmin/{adminId}")
    @Operation(summary = "회사별 관리자 삭제", description = "회사별 관리자를 삭제 합니다.")
    public ResponseEntity<ApiResponse<Boolean>> deleteCompanyAdmin(@PathVariable String adminId) {
        boolean result = superUserService.deleteCompanyAdmin(adminId);
        return ResponseEntity.ok(ApiResponse.success("관리자가 성공적으로 삭제되었습니다.", result));
    }
}
