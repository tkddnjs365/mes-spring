package com.mes.mes_boot.system.program.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMenuDto {
    private String name;
    private String description;
    private Number sortOrder;
    private String parentId;
    private String saveType;
}
