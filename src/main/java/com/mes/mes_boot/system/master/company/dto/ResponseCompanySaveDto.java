package com.mes.mes_boot.system.master.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanySaveDto {
    private boolean success;
    private String error;
    private String coIdx;
}