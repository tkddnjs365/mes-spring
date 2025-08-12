package com.mes.mes_boot.system.master.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemSaveDto {
    private boolean success;    // 성공 여부
    private String error;       // 오류 메시지 (실패시)
    private String itemIdx;     // 품목 인덱스 (성공시)
}