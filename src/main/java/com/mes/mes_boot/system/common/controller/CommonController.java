package com.mes.mes_boot.system.common.controller;

import com.mes.mes_boot.system.common.dto.CommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonDto;
import com.mes.mes_boot.system.common.dto.ResponseCommonDto;
import com.mes.mes_boot.system.common.service.CommonService;
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
@RequestMapping("/api/common")
@Tag(name = "Common", description = "공통 관련 API")
public class CommonController {
    private final CommonService commonService;

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
}
