package com.mes.mes_boot.system.program.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProgDto {
    private String programId;
    private String progName;
    private String path;
    private String description;
}
