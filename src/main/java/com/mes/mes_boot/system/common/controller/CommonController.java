package com.mes.mes_boot.system.common.controller;

import com.mes.mes_boot.system.common.dto.CommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonSaveDto;
import com.mes.mes_boot.system.common.service.CommonService;
import com.mes.mes_boot.util.ApiResponse;
import com.mes.mes_boot.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@Tag(name = "Common", description = "공통 관련 API")
public class CommonController {
    private final CommonService commonService;

    /**
     * 회사별 공통데이터 그룹별 데이터 조회
     *
     * @param request 조회 조건
     * @return 그룹별 공통 데이터 목록
     */
    @PostMapping("/groupDtlSelect")
    @Operation(summary = "회사별 공통데이터 그룹별 데이터 조회", description = "회사별 공통데이터 그룹별 데이터 조회")
    public ResponseEntity<ApiResponse<List<CommonDto>>> getCompGroupDtl(@RequestBody RequestCommonDto request) {
        List<CommonDto> result = commonService.getCompGroupDtl(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 공통 데이터 조회
     *
     * @param request 조회 조건
     * @return 공통 데이터 목록
     */
    @PostMapping("/select")
    @Operation(summary = "공통데이터 조회", description = "공통데이터 조회")
    public ResponseEntity<ApiResponse<List<CommonDto>>> getCommon(@RequestBody RequestCommonDto request) {
        List<CommonDto> result = commonService.getCommon(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 공통 코드 생성
     *
     * @param request 생성할 공통 코드 정보
     * @return 생성 결과
     */
    @PostMapping("/insertCommon")
    @Operation(summary = "공통 코드 생성", description = "새로운 공통 코드를 생성합니다")
    public ResponseEntity<ApiResponse<Boolean>> insertCommon(@RequestBody @Validated(ValidationGroups.Create.class) RequestCommonSaveDto request) {
        boolean result = commonService.insertCommon(request);
        return ResponseEntity.ok(ApiResponse.success("공통 코드가 생성되었습니다.", result));
    }

    /**
     * 공통 코드 삭제
     *
     * @param request 삭제할 공통 코드 정보
     * @return 삭제 결과
     */
    @DeleteMapping("/deleteCommon")
    @Operation(summary = "공통 코드 삭제", description = "기존 공통 코드를 삭제합니다")
    public ResponseEntity<ApiResponse<Boolean>> deleteCommon(@RequestBody @Validated(ValidationGroups.Delete.class) RequestCommonSaveDto request) {
        boolean result = commonService.deleteCommon(request);
        return ResponseEntity.ok(ApiResponse.success("공통 코드가 삭제되었습니다.", result));
    }

    /**
     * 공통 코드 수정
     *
     * @param request 수정할 공통 코드 정보
     * @return 수정 결과
     */
    @PutMapping("/updateCommon")
    @Operation(summary = "공통 코드 수정", description = "기존 공통 코드 정보를 수정합니다")
    public ResponseEntity<ApiResponse<Boolean>> updateCommon(@RequestBody @Validated(ValidationGroups.Update.class) RequestCommonSaveDto request) {
        boolean result = commonService.updateCommon(request);
        return ResponseEntity.ok(ApiResponse.success("공통 코드가 수정되었습니다.", result));
    }
}
