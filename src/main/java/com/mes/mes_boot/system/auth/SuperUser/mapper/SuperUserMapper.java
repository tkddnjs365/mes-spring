package com.mes.mes_boot.system.auth.SuperUser.mapper;

import com.mes.mes_boot.system.auth.SuperUser.dto.company.CompanyAdminDto;
import com.mes.mes_boot.system.auth.SuperUser.dto.login.SuperUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper // MyBatis 매퍼 인터페이스임을 Spring에 알림
public interface SuperUserMapper {

    // paramMap의 userId, password로 데이터베이스에서 사용자 조회
    SuperUserDto superUserLogin(Map<String, Object> paramMap); // 로그인 처리 메서드 선언
    
    /* 회사 생성 */
    int createCompany(Map<String, Object> paramMap);

    /* 회사별 관리자 조회 */
    List<CompanyAdminDto> getComapnyAdmin(Map<String, Object> paramMap);

    /* 회사별 관리자 생성 */
    int createCompanyAdmin(Map<String, Object> paramMap);

    /* 회사별 관리자 삭제 */
    int deleteCompanyAdmin(Map<String, Object> paramMap);
}
