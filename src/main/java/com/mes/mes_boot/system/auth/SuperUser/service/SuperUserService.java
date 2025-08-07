package com.mes.mes_boot.system.auth.SuperUser.service;

import com.mes.mes_boot.system.auth.SuperUser.dto.company.CompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.company.RequestCreateCompanyDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.RequestSuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.SuperUserDto;
import com.mes.mes_boot.system.auth.SuperUser.mapper.SuperUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // 이 클래스가 서비스 계층임을 Spring에 알림 (비즈니스 로직 처리)
public class SuperUserService {
    private final SuperUserMapper superUserMapper; // 매퍼 인터페이스 의존성
    Map<String, Object> paramMap = new HashMap<>();//파라 미터를 담을 Map 생성

    @Autowired // 생성자 주입으로 의존성 주입
    public SuperUserService(SuperUserMapper superUserMapper) {
        this.superUserMapper = superUserMapper;
    }

    /* 로그인 */
    public SuperUserDto login(RequestSuperUserDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("userId", request.getUserId().trim());
            paramMap.put("password", request.getPassword());

            return superUserMapper.superUserLogin(paramMap);  // 매퍼의 로그인 메서드 호출하여 결과 반환
        } catch (Exception e) {
            return null;
        }
    }

    /* 회사 생성 */
    public boolean createCompany(RequestCreateCompanyDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("code", request.getCode());
            paramMap.put("name", request.getName());
            paramMap.put("description", request.getDescription());

            int result = superUserMapper.createCompany(paramMap);
            return result > 0;  // 1개 이상 삽입되었으면 성공

        } catch (Exception e) {
            return false;
        }
    }

    /* 회사별 관리자 조회 */
    public List<CompanyAdminDto> getComapnyAdmin(String company_code) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("company_code", company_code);

            return superUserMapper.getComapnyAdmin(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 회사별 관리자 생성 */
    public boolean createCompanyAdmin(RequestCreateCompanyAdminDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyCode", request.getCompanyCode());
            paramMap.put("userId", request.getUserId());
            paramMap.put("password", request.getPassword());
            paramMap.put("name", request.getName());

            int result = superUserMapper.createCompanyAdmin(paramMap);
            return result > 0;  // 1개 이상 삽입되었으면 성공

        } catch (Exception e) {
            return false;
        }
    }

    /* 회사별 관리자 삭제 */
    public boolean deleteCompanyAdmin(String adminId) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("adminId", adminId);

            int result = superUserMapper.deleteCompanyAdmin(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
