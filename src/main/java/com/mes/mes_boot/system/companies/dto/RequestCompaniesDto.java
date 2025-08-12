package com.mes.mes_boot.system.companies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompaniesDto {
    private String company_code;
    private String company_idx;
}
