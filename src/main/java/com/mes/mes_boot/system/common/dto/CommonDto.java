package com.mes.mes_boot.system.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDto {
    private String dataId;
    private String companyIdx;
    private String groupId;
    private String keyId;
    private String value;
    private String description;
    private Integer sortOrder;
    private Integer useYn;
}
