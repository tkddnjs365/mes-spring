package com.mes.mes_boot.system.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommonSaveDto {
    private String companyIdx;
    private String groupId;
    private String value;
    private String sortOrder;
    private String useYn;
    private String dataId;
    private String keyId;
}
