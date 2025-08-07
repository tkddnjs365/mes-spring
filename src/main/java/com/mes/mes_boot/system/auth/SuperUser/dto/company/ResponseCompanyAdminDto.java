package com.mes.mes_boot.system.auth.SuperUser.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCompanyAdminDto {
    private boolean success;
    private String message;
    private List<CompanyAdminDto> companyAdminDtoList;
}
