package com.mes.mes_boot.system.companies.service;

import com.mes.mes_boot.system.companies.dto.CompaniesDto;
import com.mes.mes_boot.system.companies.dto.RequestCompaniesDto;
import com.mes.mes_boot.system.companies.mapper.CompaniesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // 이 클래스가 서비스 계층임을 Spring에 알림 (비즈니스 로직 처리)
public class CompaniesService {
    private final CompaniesMapper companiesMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public CompaniesService(CompaniesMapper companiesMapper) {
        this.companiesMapper = companiesMapper;
    }

    /* 전체 조회 */
    public List<CompaniesDto> getCompanyAll(String type) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("type", type);

            return companiesMapper.companySelectAll(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 일반 조회 */
    public List<CompaniesDto> getCompany(RequestCompaniesDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("company_idx", request.getCompany_idx());
            paramMap.put("company_code", request.getCompany_code());

            return companiesMapper.companySelect(paramMap);
        } catch (Exception e) {
            return null;
        }
    }
}
