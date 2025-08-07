package com.mes.mes_boot.system.program.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestConnectCompProgDto {
    private String companyCode;
    private String programId;
}
