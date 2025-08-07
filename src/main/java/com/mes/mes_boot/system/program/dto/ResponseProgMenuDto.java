package com.mes.mes_boot.system.program.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProgMenuDto {
    private boolean success;
    private String message;
    private List<ProgMenuDto> programs;
}
