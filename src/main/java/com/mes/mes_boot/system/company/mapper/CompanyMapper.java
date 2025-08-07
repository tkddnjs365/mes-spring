package com.mes.mes_boot.system.company.mapper;

import com.mes.mes_boot.system.company.dto.CompanyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {

    //전체 조회
    List<CompanyDto> companySelectAll(Map<String, Object> paramMap);

    //일반 조회
    List<CompanyDto> companySelect(Map<String, Object> paramMap);
}
