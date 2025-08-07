package com.mes.mes_boot.system.company.service;

import com.mes.mes_boot.system.company.dto.CompanyDto;
import com.mes.mes_boot.system.company.dto.RequestCompanyDto;
import com.mes.mes_boot.system.company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // 이 클래스가 서비스 계층임을 Spring에 알림 (비즈니스 로직 처리)
public class CompanyService {
    private final CompanyMapper companyMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    /* 전체 조회 */
    public List<CompanyDto> getCompanyAll(String type) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("type", type);

            return companyMapper.companySelectAll(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 일반 조회 */
    public List<CompanyDto> getCompany(RequestCompanyDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("company_idx", request.getCompany_idx());
            paramMap.put("company_code", request.getCompany_code());

            return companyMapper.companySelect(paramMap);
        } catch (Exception e) {
            return null;
        }
    }
}
