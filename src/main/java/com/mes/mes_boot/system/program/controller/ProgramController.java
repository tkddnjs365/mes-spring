package com.mes.mes_boot.system.program.controller;

import com.mes.mes_boot.system.program.dto.*;
import com.mes.mes_boot.system.program.service.ProgramService;
import com.mes.mes_boot.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 프로그램 및 메뉴 관리 컨트롤러
 * 시스템 내 프로그램과 메뉴의 등록, 수정, 삭제 및 회사와의 연결 기능을 제공
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/program")
@Tag(name = "Program", description = "프로그램 관련 API")
public class ProgramController {
    private final ProgramService programService;

    /**
     * 프로그램 전체 조회
     *
     * @return 전체 프로그램 목록
     */
    @GetMapping
    @Operation(summary = "프로그램 전체 조회", description = "프로그램 전체 조회")
    public ResponseEntity<ApiResponse<List<ProgramDto>>> getPrograms() {
        List<ProgramDto> result = programService.getPrograms();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 프로그램 추가
     *
     * @param request 추가할 프로그램 정보
     * @return 추가 결과
     */
    @PostMapping("/progCreate")
    @Operation(summary = "프로그램 추가", description = "프로그램 추가")
    public ResponseEntity<ApiResponse<Boolean>> createProgram(@RequestBody RequestProgDto request) {
        boolean result = programService.createProgram(request);
        return ResponseEntity.ok(ApiResponse.success("프로그램이 성공적으로 추가되었습니다.", result));
    }

    /**
     * 프로그램 삭제
     *
     * @param request 삭제할 프로그램 정보
     * @return 삭제 결과
     */
    @DeleteMapping("/progDelete")
    @Operation(summary = "프로그램 삭제", description = "프로그램 삭제")
    public ResponseEntity<ApiResponse<Boolean>> deleteProgram(@RequestBody RequestProgDto request) {
        boolean result = programService.deleteProgram(request);
        return ResponseEntity.ok(ApiResponse.success("프로그램이 성공적으로 삭제되었습니다.", result));
    }

    /**
     * 프로그램 수정
     *
     * @param request 수정할 프로그램 정보
     * @return 수정 결과
     */
    @PutMapping("/progUpdate")
    @Operation(summary = "프로그램 수정", description = "프로그램 수정")
    public ResponseEntity<ApiResponse<Boolean>> updateProgram(@RequestBody RequestProgDto request) {
        boolean result = programService.updateProgram(request);
        return ResponseEntity.ok(ApiResponse.success("프로그램이 성공적으로 수정되었습니다.", result));
    }

    /**
     * 회사별 프로그램 연결 조회
     *
     * @param companyCode 회사 코드
     * @return 회사에 연결된 프로그램 목록
     */
    @GetMapping("/companyProg/{companyCode}")
    @Operation(summary = "회사별 프로그램 연결 조회", description = "회사별 프로그램 연결 조회 합니다")
    public ResponseEntity<ApiResponse<List<ProgCompDto>>> getCompanyPrograms(@PathVariable String companyCode) {
        List<ProgCompDto> result = programService.getCompanyPrograms(companyCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 회사-프로그램 연결
     *
     * @param request 연결 요청 정보
     * @return 연결 결과
     */
    @PostMapping("/connectCompProg")
    @Operation(summary = "회사-프로그램 연결", description = "회사-프로그램 연결 합니다")
    public ResponseEntity<ApiResponse<Boolean>> connectCompanyProgram(@RequestBody RequestConnectCompProgDto request) {
        boolean result = programService.connectCompanyProgram(request);
        return ResponseEntity.ok(ApiResponse.success("회사-프로그램 연결이 완료되었습니다.", result));
    }

    /**
     * 회사-프로그램 연결 해제
     *
     * @param request 연결 해제 요청 정보
     * @return 해제 결과
     */
    @DeleteMapping("/disConnectCompProg")
    @Operation(summary = "회사-프로그램 연결 해제", description = "회사-프로그램 연결 해제 합니다")
    public ResponseEntity<ApiResponse<Boolean>> disconnectCompanyProgram(@RequestBody RequestConnectCompProgDto request) {
        boolean result = programService.disconnectCompanyProgram(request);
        return ResponseEntity.ok(ApiResponse.success("회사-프로그램 연결이 해제되었습니다.", result));
    }

    /**
     * 회사별 프로그램 목록
     *
     * @param company_idx 회사 인덱스
     * @return 회사별 프로그램 목록
     */
    @GetMapping("/progMenu/{company_idx}")
    @Operation(summary = "회사별 프로그램 목록", description = "회사별 프로그램 목록")
    public ResponseEntity<ApiResponse<List<ProgMenuDto>>> getProgMenuCompany(@PathVariable String company_idx) {
        List<ProgMenuDto> result = programService.getProgMenuCompany(company_idx);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 메뉴 조회
     *
     * @return 메뉴 목록
     */
    @GetMapping("/menuCategory")
    @Operation(summary = "메뉴 목록 조회", description = "메뉴 목록 조회")
    public ResponseEntity<ApiResponse<List<MenuDto>>> getMenuCategories() {
        List<MenuDto> result = programService.getMenuCategories();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 메뉴 생성
     *
     * @param request 생성할 메뉴 정보
     * @return 생성 결과
     */
    @PostMapping("/menuCreate")
    @Operation(summary = "메뉴 생성", description = "메뉴 생성")
    public ResponseEntity<ApiResponse<Boolean>> createMenuCategory(@RequestBody RequestMenuDto request) {
        boolean result = programService.createMenuCategory(request);
        return ResponseEntity.ok(ApiResponse.success("메뉴가 성공적으로 생성되었습니다.", result));
    }

    /**
     * 중메뉴 프로그램 연결 조회
     *
     * @param menuId 메뉴 ID
     * @return 연결된 프로그램 목록
     */
    @GetMapping("/menuProg/{menuId}")
    @Operation(summary = "중메뉴 프로그램 연결 조회", description = "중메뉴 프로그램 연결 조회")
    public ResponseEntity<ApiResponse<List<MenuProgDto>>> getMenuLinkPrograms(@PathVariable String menuId) {
        List<MenuProgDto> result = programService.getMenuLinkPrograms(menuId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 중메뉴-프로그램 연결
     *
     * @param request 연결 요청 정보
     * @return 연결 결과
     */
    @PostMapping("/menuProgConnect")
    @Operation(summary = "중메뉴-프로그램 연결", description = "중메뉴-프로그램 연결 합니다")
    public ResponseEntity<ApiResponse<Boolean>> connectMenuProgram(@RequestBody RequestMenuProgDto request) {
        boolean result = programService.connectMenuProgram(request);
        return ResponseEntity.ok(ApiResponse.success("중메뉴-프로그램 연결이 완료되었습니다.", result));
    }

    /**
     * 중메뉴-프로그램 연결 해제
     *
     * @param request 연결 해제 요청 정보
     * @return 해제 결과
     */
    @DeleteMapping("/menuProgDisConnect")
    @Operation(summary = "중메뉴-프로그램 연결 해제", description = "중메뉴-프로그램 해제 합니다")
    public ResponseEntity<ApiResponse<Boolean>> disconnectMenuProgram(@RequestBody RequestMenuProgDto request) {
        boolean result = programService.disconnectMenuProgram(request);
        return ResponseEntity.ok(ApiResponse.success("중메뉴-프로그램 연결이 해제되었습니다.", result));
    }

    /**
     * 메뉴별 연결된 프로그램 전체 조회
     *
     * @return 전체 메뉴-프로그램 연결 정보
     */
    @GetMapping("/getMenuProg")
    @Operation(summary = "메뉴별 연결된 프로그램 전체 조회", description = "메뉴별 연결된 프로그램 전체 조회")
    public ResponseEntity<ApiResponse<List<ProgMenuDto>>> getMenuProg() {
        List<ProgMenuDto> result = programService.getMenuProg();
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}