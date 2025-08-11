package com.mes.mes_boot.system.master.item.controller;

import com.mes.mes_boot.system.master.item.dto.*;
import com.mes.mes_boot.system.master.item.service.ItemService;
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
@RequestMapping("/api/item")
@Tag(name = "Item", description = "품목 관련 API")
public class ItemController {
    private final ItemService itemService;

    /* 품목 조회 */
    @PostMapping("/itemSelect")
    @Operation(summary = "품목 조회", description = "품목 조회")
    public ResponseEntity<ResponseItemDto> getItemList(@RequestBody RequestItemDto request) {
        List<ItemDto> re_dto = itemService.getItemList(request);

        boolean isSuccess = re_dto != null;
        String message = isSuccess ? "성공" : "서버 오류";

        ResponseItemDto response = ResponseItemDto.builder()
                .success(isSuccess)
                .message(message)
                .itemList(re_dto)
                .build();

        return ResponseEntity.status(isSuccess ? 200 : 500).body(response);
    }

    /* 품목 저장 */
    @PostMapping("/itemSave")
    @Operation(summary = "품목 저장", description = "품목 저장 (신규/수정)")
    public ResponseEntity<ResponseItemSaveDto> saveItem(@RequestBody RequestItemSaveDto request) {
        ResponseItemSaveDto response = itemService.saveItem(request);

        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
