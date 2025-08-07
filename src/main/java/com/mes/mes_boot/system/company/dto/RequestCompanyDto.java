package com.mes.mes_boot.system.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompanyDto {
    private String company_code;
    private String company_idx;
}
