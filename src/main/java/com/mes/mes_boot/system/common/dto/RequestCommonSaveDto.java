package com.mes.mes_boot.system.common.dto;

import com.mes.mes_boot.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 공통 코드 저장/수정/삭제 요청 DTO
 * 공통 코드의 CRUD 작업 시 사용되는 데이터 전송 객체
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommonSaveDto {
    
    /**
     * 회사 인덱스
     */
    private String companyIdx;
    
    /**
     * 그룹 ID
     */
    private String groupId;
    
    /**
     * 코드 값
     */
    private String value;
    
    /**
     * 정렬 순서
     */
    private String sortOrder;
    
    /**
     * 사용 여부 (Y/N)
     */
    private String useYn;
    
    /**
     * 데이터 ID
     */
    private String dataId;
    
    /**
     * 키 ID
     */
    private String keyId;
}
