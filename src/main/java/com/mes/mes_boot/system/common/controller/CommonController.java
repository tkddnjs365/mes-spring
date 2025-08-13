package com.mes.mes_boot.system.common.controller;

import com.mes.mes_boot.system.common.dto.CommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonSaveDto;
import com.mes.mes_boot.system.common.dto.ResponseCommonDto;
import com.mes.mes_boot.system.common.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@Tag(name = "Common", description = "공통 관련 API")
public class CommonController {
    private final CommonService commonService;

    @PostMapping("/groupDtlSelect")
    @Operation(summary = "회사별 공통데이터 그룹별 데이터 조회", description = "회사별 공통데이터 그룹별 데이터 조회")
    public ResponseEntity<ResponseCommonDto> getCompGroupDtl(@RequestBody RequestCommonDto request) {
        List<CommonDto> re_dto = commonService.getCompGroupDtl(request);

        boolean isSuccess = re_dto != null;
        String message = isSuccess ? "성공" : "서버 오류";

        ResponseCommonDto response = ResponseCommonDto.builder()
                .success(isSuccess)
                .message(message)
                .common(re_dto)
                .build();

        return ResponseEntity.status(isSuccess ? 200 : 500).body(response);
    }

    /* 공통 데이터 조회 */
    @PostMapping("/select")
    @Operation(summary = "공통데이터 조회", description = "공통데이터 조회")
    public ResponseEntity<ResponseCommonDto> getCommon(@RequestBody RequestCommonDto request) {
        List<CommonDto> re_dto = commonService.getCommon(request);

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseCommonDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .common(null)
                            .build()
            );
        }

        ResponseCommonDto response = ResponseCommonDto.builder()
                .success(true)
                .message("성공")
                .common(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }

    /* 공통 코드 생성 */
    @PostMapping("/insertCommon")
    public ResponseEntity<Map<String, Object>> insertCommon(@RequestBody RequestCommonSaveDto request) {
        try {
            boolean result = commonService.insertCommon(request);

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

    /* 공통 코드 삭제 */
    @DeleteMapping("/deleteCommon")
    public ResponseEntity<Map<String, Object>> deleteCommon(@RequestBody RequestCommonSaveDto request) {
        try {
            boolean result = commonService.deleteCommon(request);

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

    /* 공통 코드 수정 */
    @PutMapping("/updateCommon")
    public ResponseEntity<Map<String, Object>> updateCommon(@RequestBody RequestCommonSaveDto request) {
        try {
            boolean result = commonService.updateCommon(request);

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
