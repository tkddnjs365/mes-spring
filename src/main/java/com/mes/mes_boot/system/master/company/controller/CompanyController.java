package com.mes.mes_boot.system.master.company.controller;

import com.mes.mes_boot.system.master.company.dto.CompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanySaveDto;
import com.mes.mes_boot.system.master.company.dto.ResponseCompanySaveDto;
import com.mes.mes_boot.system.master.company.service.CompanyService;
import com.mes.mes_boot.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 거래처 관리 컨트롤러
 * 거래처의 조회, 등록, 수정 기능을 제공
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companyMst")
@Tag(name = "CompanyMst", description = "거래처 관련 API")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 거래처 조회
     *
     * @param request 거래처 조회 조건
     * @return 거래처 목록
     */
    @PostMapping("/companySelect")
    @Operation(summary = "거래처 조회", description = "거래처 조회")
    public ResponseEntity<ApiResponse<List<CompanyDto>>> getCompanyList(@RequestBody RequestCompanyDto request) {
        List<CompanyDto> result = companyService.getCompanyList(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 거래처 저장 (신규/수정)
     *
     * @param request 저장할 거래처 정보
     * @return 저장 결과
     */
    @PostMapping("/companySave")
    @Operation(summary = "거래처 저장", description = "거래처 저장 (신규/수정)")
    public ResponseEntity<ApiResponse<Boolean>> saveCompany(@RequestBody RequestCompanySaveDto request) {
        ResponseCompanySaveDto serviceResponse = companyService.saveCompany(request);

        if (serviceResponse.isSuccess()) {
            return ResponseEntity.ok(ApiResponse.success("거래처가 성공적으로 저장되었습니다.", true));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error(serviceResponse.getError()));
        }
    }
}
