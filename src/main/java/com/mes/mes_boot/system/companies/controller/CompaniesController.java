package com.mes.mes_boot.system.companies.controller;

import com.mes.mes_boot.system.companies.dto.CompaniesDto;
import com.mes.mes_boot.system.companies.dto.RequestCompaniesDto;
import com.mes.mes_boot.system.companies.service.CompaniesService;
import com.mes.mes_boot.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
@Tag(name = "Company", description = "회사 관련 API")
public class CompaniesController {
    private final CompaniesService companiesService;

    /**
     * 회사목록 전체 조회
     *
     * @param type 조회 타입 (admin: 관리자용 회사코드)
     * @return 회사 목록
     */
    @GetMapping("/{type}")
    @Operation(summary = "회사목록 전체조회", description = "회사목록 전체조회 합니다")
    public ResponseEntity<ApiResponse<List<CompaniesDto>>> getCompanyAll(@PathVariable String type) {
        List<CompaniesDto> result = companiesService.getCompanyAll(type);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 회사 목록 조회
     *
     * @param request 회사 조회 조건
     * @return 조회된 회사 목록
     */
    @PostMapping("/select")
    @Operation(summary = "회사조회_회사코드", description = "회사코드로 회사를 조회")
    public ResponseEntity<ApiResponse<List<CompaniesDto>>> getCompany(@RequestBody RequestCompaniesDto request) {
        List<CompaniesDto> result = companiesService.getCompany(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
