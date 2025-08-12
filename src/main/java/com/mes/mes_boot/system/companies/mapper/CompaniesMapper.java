package com.mes.mes_boot.system.companies.mapper;

import com.mes.mes_boot.system.companies.dto.CompaniesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompaniesMapper {

    //전체 조회
    List<CompaniesDto> companySelectAll(Map<String, Object> paramMap);

    //일반 조회
    List<CompaniesDto> companySelect(Map<String, Object> paramMap);
}
