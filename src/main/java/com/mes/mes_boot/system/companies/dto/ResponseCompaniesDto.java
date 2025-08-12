package com.mes.mes_boot.system.companies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompaniesDto {
    private boolean success;
    private String message;
    private List<CompaniesDto> companies;
}
