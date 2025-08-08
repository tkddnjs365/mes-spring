package com.mes.mes_boot.system.program.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private Integer sortOrder;
    private String parentId;
}
