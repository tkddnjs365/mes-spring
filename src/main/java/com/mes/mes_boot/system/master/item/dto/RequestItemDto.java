package com.mes.mes_boot.system.master.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestItemDto {
    private String companyIdx;
    private String item;
    private String itemIdx;
    private String ItemType;
    private String ItemYn;
}
