package com.mes.mes_boot.system.companies.controller;

import com.mes.mes_boot.system.companies.dto.CompaniesDto;
import com.mes.mes_boot.system.companies.dto.RequestCompaniesDto;
import com.mes.mes_boot.system.companies.dto.ResponseCompaniesDto;
import com.mes.mes_boot.system.companies.service.CompaniesService;
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

    /* 회사목록 전체 조회 */
    @GetMapping("/{type}") // Type [ admin : 관리자용 회사코드 ]
    @Operation(summary = "회사목록 전체조회", description = "회사목록 전체조회 합니다")
    public ResponseEntity<ResponseCompaniesDto> getCompanyAll(@PathVariable String type) {
        List<CompaniesDto> re_dto = companiesService.getCompanyAll(type);

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseCompaniesDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .companies(null)
                            .build()
            );
        }

        ResponseCompaniesDto response = ResponseCompaniesDto.builder()
                .success(true)
                .message("성공")
                .companies(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }

    /* 회사목록 조회 */
    @PostMapping("/select")
    @Operation(summary = "회사조회_회사코드", description = "회사코드로 회사를 조회")
    public ResponseEntity<ResponseCompaniesDto> getCompany(@RequestBody RequestCompaniesDto request) {
        List<CompaniesDto> re_dto = companiesService.getCompany(request);

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseCompaniesDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .companies(null)
                            .build()
            );
        }

        ResponseCompaniesDto response = ResponseCompaniesDto.builder()
                .success(true)
                .message("성공")
                .companies(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }
}
