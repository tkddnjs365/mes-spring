package com.mes.mes_boot.system.auth.SuperUser.dto.company;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyAdminDto {
    private String userIdx;
    private String companyIdx;
    private String name;
    private String userId;
    private String createdAt;
    private String companyCode;
}
