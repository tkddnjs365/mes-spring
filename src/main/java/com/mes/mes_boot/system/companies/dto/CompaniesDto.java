package com.mes.mes_boot.system.companies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompaniesDto {
    private String id;
    private String code;
    private String name;
    private Boolean isActive;
    private String description;
    private String createdAt;
    private String updatedAt;

    // toString() 오버라이드
    @Override
    public String toString() {
        return "CompanyDto{code='" + code + "', name='" + name + "', isActive=" + isActive + "}";
    }

}
