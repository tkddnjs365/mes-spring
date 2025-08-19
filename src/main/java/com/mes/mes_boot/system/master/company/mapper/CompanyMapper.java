package com.mes.mes_boot.system.master.company.mapper;

import com.mes.mes_boot.system.master.company.dto.CompanyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {
    /* 거래처 조회 */
    List<CompanyDto> getCompanyList(Map<String, Object> paramMap);

    /* 거래처 중복 확인 */
    int checkDuplicateCompCode(Map<String, Object> paramMap);

    /* 다음 co_idx 조회 */
    Integer getNextCoIdx();

    /* 거래처 등록 */
    int insertCompany(Map<String, Object> paramMap);

    /* 거래처코드 조회 */
    String getCompCd(Map<String, Object> paramMap);

    /* 거래처 수정 */
    int updateCompany(Map<String, Object> paramMap);

    /* 거래처 유형 조회 */
    List<String> getCompanyCoType(String coIdx);

    /* 거래처 유형 삭제 */
    int deleteCompanyCoType(String coIdx);

    /* 거래처 유형 저장 */
    int insertCompanyCoType(Map<String, Object> paramMap);
}