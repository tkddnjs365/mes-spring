package com.mes.mes_boot.system.master.item.controller;

import com.mes.mes_boot.system.master.item.dto.ItemDto;
import com.mes.mes_boot.system.master.item.dto.RequestItemDto;
import com.mes.mes_boot.system.master.item.dto.RequestItemSaveDto;
import com.mes.mes_boot.system.master.item.dto.ResponseItemSaveDto;
import com.mes.mes_boot.system.master.item.service.ItemService;
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
 * 품목 관리 컨트롤러
 * 품목의 조회, 등록, 수정 기능을 제공
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
@Tag(name = "Item", description = "품목 관련 API")
public class ItemController {
    private final ItemService itemService;

    /**
     * 품목 조회
     *
     * @param request 품목 조회 조건
     * @return 품목 목록
     */
    @PostMapping("/itemSelect")
    @Operation(summary = "품목 조회", description = "품목 조회")
    public ResponseEntity<ApiResponse<List<ItemDto>>> getItemList(@RequestBody RequestItemDto request) {
        List<ItemDto> result = itemService.getItemList(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 품목 저장 (신규/수정)
     *
     * @param request 저장할 품목 정보
     * @return 저장 결과
     */
    @PostMapping("/itemSave")
    @Operation(summary = "품목 저장", description = "품목 저장 (신규/수정)")
    public ResponseEntity<ApiResponse<Boolean>> saveItem(@RequestBody RequestItemSaveDto request) {
        ResponseItemSaveDto serviceResponse = itemService.saveItem(request);

        if (serviceResponse.isSuccess()) {
            return ResponseEntity.ok(ApiResponse.success("품목이 성공적으로 저장되었습니다.", true));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error(serviceResponse.getError()));
        }
    }
}
