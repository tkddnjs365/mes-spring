package com.mes.mes_boot.system.master.company.controller;

import com.mes.mes_boot.system.master.company.dto.*;
import com.mes.mes_boot.system.master.company.service.CompanyService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companyMst")
@Tag(name = "CompanyMst", description = "거래처 관련 API")
public class CompanyController {
    private final CompanyService companyService;

    /* 거래처 조회 */
    @PostMapping("/companySelect")
    @Operation(summary = "거래처", description = "거래처 조회")
    public ResponseEntity<ResponseCompanyDto> getCompanyList(@RequestBody RequestCompanyDto request) {
        List<CompanyDto> re_dto = companyService.getCompanyList(request);

        boolean isSuccess = re_dto != null;
        String message = isSuccess ? "성공" : "서버 오류";

        ResponseCompanyDto response = ResponseCompanyDto.builder()
                .success(isSuccess)
                .message(message)
                .companyList(re_dto)
                .build();

        return ResponseEntity.status(isSuccess ? 200 : 500).body(response);
    }

    /* 품목 저장 */
    @PostMapping("/itemSave")
    @Operation(summary = "품목 저장", description = "품목 저장 (신규/수정)")
    public ResponseEntity<ResponseCompanySaveDto> saveItem(@RequestBody RequestCompanySaveDto request) {
        ResponseCompanySaveDto response = companyService.saveCompany(request);

        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
