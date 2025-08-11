package com.mes.mes_boot.system.master.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestItemSaveDto {
    private String curItemIdx;      // 현재 품목 인덱스 (수정시 사용, 신규시 빈값)
    private String companyIdx;      // 회사 인덱스
    private String itemCd;          // 품목 코드
    private String itemNm;          // 품목명
    private String itemSpec;        // 품목 규격
    private String itemType;        // 품목 타입
    private String itemUnit;        // 품목 단위
    private String useYn;           // 사용 여부
    private String etc;             // 기타
}