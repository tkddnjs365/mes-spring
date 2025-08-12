package com.mes.mes_boot.system.master.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private String coIdx;
    private String coCd;
    private String coNm;
    private String useYn;
    private String compAddr;
    private String compType;
    private String compItem;
    private String compCurr;
    private String bizNo;
    private String ceoNm;
    private String tel;
    private String fax;
    private String email;
    private String country;
    private String createdAt;
    private String createdUser;
    private String updatedAt;
    private String updatedUser;

    private String compTypeIdx;
    private String compItemIdx;
    private String compCurrIdx;
    private String createdUserIdx;
    private String updatedUserIdx;
}
