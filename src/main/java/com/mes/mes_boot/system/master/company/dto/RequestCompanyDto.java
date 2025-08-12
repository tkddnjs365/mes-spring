package com.mes.mes_boot.system.master.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompanyDto {
    private String companyIdx;
    private String company;
    private String coIdx;
    private String useYn;
}
