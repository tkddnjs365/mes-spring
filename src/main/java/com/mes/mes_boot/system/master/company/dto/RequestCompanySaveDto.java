package com.mes.mes_boot.system.master.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCompanySaveDto {
    private String curCoIdx;      // 현재 인덱스
    private String companyIdx;

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
    private String userIdx;
    private String[] coType;
}