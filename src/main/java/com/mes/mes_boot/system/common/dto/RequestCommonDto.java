package com.mes.mes_boot.system.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommonDto {
    private String groupId;
    private String useYn;
    private String companyIdx;
}
