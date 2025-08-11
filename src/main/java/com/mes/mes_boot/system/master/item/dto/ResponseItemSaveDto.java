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
    private boolean success;
    private String error;
    private String itemIdx;
}