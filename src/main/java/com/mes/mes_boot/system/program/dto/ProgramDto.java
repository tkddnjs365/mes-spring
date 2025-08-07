package com.mes.mes_boot.system.program.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramDto {
    private String id;
    private String name;
    private String description;
    private String path;
    private String createdAt;
    private String updatedAt;
}
