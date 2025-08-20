package com.mes.mes_boot.validation;

/**
 * 유효성 검증 그룹 인터페이스
 * 동일한 DTO에서 상황별로 다른 유효성 검증 규칙을 적용하기 위한 마커 인터페이스들
 */
public class ValidationGroups {
    
    /**
     * 생성 시 유효성 검증 그룹
     */
    public interface Create {}
    
    /**
     * 수정 시 유효성 검증 그룹
     */
    public interface Update {}
    
    /**
     * 삭제 시 유효성 검증 그룹
     */
    public interface Delete {}
    
    /**
     * 조회 시 유효성 검증 그룹
     */
    public interface Read {}
}