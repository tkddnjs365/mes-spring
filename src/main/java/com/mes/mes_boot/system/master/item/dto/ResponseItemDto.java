package com.mes.mes_boot.system.master.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemDto {
    private boolean success;
    private String message;
    private List<ItemDto> itemList;
}
