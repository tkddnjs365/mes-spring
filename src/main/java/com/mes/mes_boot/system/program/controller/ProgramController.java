package com.mes.mes_boot.system.program.controller;

import com.mes.mes_boot.system.program.dto.*;
import com.mes.mes_boot.system.program.service.ProgramService;
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
@RequestMapping("/api/program")
@Tag(name = "Program", description = "프로그램 관련 API")
public class ProgramController {
    private final ProgramService programService;

    /* 프로그램 전체 조회 */
    @GetMapping
    @Operation(summary = "프로그램 전체 조회", description = "프로그램 전체 조회")
    public ResponseEntity<ResponseProgramDto> getPrograms() {
        List<ProgramDto> re_dto = programService.getPrograms();

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseProgramDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .programs(null)
                            .build()
            );
        }

        ResponseProgramDto response = ResponseProgramDto.builder()
                .success(true)
                .message("성공")
                .programs(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }

    /* 프로그램 추가 */
    @PostMapping("/progCreate")
    @Operation(summary = "프로그램 추가", description = "프로그램 추가")
    public ResponseEntity<Map<String, Object>> createProgram(@RequestBody RequestProgDto request) {
        try {
            boolean result = programService.createProgram(request);

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

    /* 프로그램 삭제 */
    @DeleteMapping("/progDelete")
    @Operation(summary = "프로그램 삭제", description = "프로그램 삭제")
    public ResponseEntity<Map<String, Object>> deleteProgram(@RequestBody RequestProgDto request) {
        try {
            boolean result = programService.deleteProgram(request);

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

    /* 프로그램 수정 */
    @PutMapping("/progUpdate")
    @Operation(summary = "프로그램 수정", description = "프로그램 수정")
    public ResponseEntity<Map<String, Object>> updateProgram(@RequestBody RequestProgDto request) {
        try {
            boolean result = programService.updateProgram(request);

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

    /* 회사별 프로그램 연결 조회 */
    @GetMapping("/companyProg/{companyCode}")
    @Operation(summary = "회사별 프로그램 연결 조회", description = "회사별 프로그램 연결 조회 합니다")
    public ResponseEntity<ResponseProgCompDto> getCompanyPrograms(@PathVariable String companyCode) {
        List<ProgCompDto> re_dto = programService.getCompanyPrograms(companyCode);

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseProgCompDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .programs(null)
                            .build()
            );
        }

        ResponseProgCompDto response = ResponseProgCompDto.builder()
                .success(true)
                .message("성공")
                .programs(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }

    /* 회사-프로그램 연결 */
    @PostMapping("/connectCompProg")
    @Operation(summary = "회사-프로그램 연결", description = "회사-프로그램 연결 합니다")
    public ResponseEntity<Map<String, Object>> connectCompanyProgram(@RequestBody RequestConnectCompProgDto request) {
        try {
            boolean result = programService.connectCompanyProgram(request);

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

    /* 회사-프로그램 연결 해제 */
    @DeleteMapping("/disConnectCompProg")
    @Operation(summary = "회사-프로그램 연결 해제", description = "회사-프로그램 연결 해제 합니다")
    public ResponseEntity<Map<String, Object>> disconnectCompanyProgram(@RequestBody RequestConnectCompProgDto request) {
        try {
            boolean result = programService.disconnectCompanyProgram(request);

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

    /* 회사별 프로그램 목록 */
    @GetMapping("/progMenu/{company_idx}")
    @Operation(summary = "회사별 프로그램 목록", description = "회사별 프로그램 목록")
    public ResponseEntity<ResponseProgMenuDto> getProgMenuCompany(@PathVariable String company_idx) {
        List<ProgMenuDto> re_dto = programService.getProgMenuCompany(company_idx);

        if (re_dto == null) {
            return ResponseEntity.status(500).body(
                    ResponseProgMenuDto.builder()
                            .success(false)
                            .message("서버 오류")
                            .programs(null)
                            .build()
            );
        }

        ResponseProgMenuDto response = ResponseProgMenuDto.builder()
                .success(true)
                .message("성공")
                .programs(re_dto)
                .build();

        return ResponseEntity.ok(response);
    }
}
