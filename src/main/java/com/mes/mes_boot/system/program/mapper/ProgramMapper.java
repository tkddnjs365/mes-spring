package com.mes.mes_boot.system.program.mapper;

import com.mes.mes_boot.system.program.dto.ProgCompDto;
import com.mes.mes_boot.system.program.dto.ProgMenuDto;
import com.mes.mes_boot.system.program.dto.ProgramDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProgramMapper {

    /* 프로그램 전체 조회 */
    List<ProgramDto> getPrograms();

    /* 프로그램 추가 */
    int createProgram(Map<String, Object> paramMap);

    /* 프로그램 삭제 */
    int deleteProgram(Map<String, Object> paramMap);

    /* 프로그램 수정 */
    int updateProgram(Map<String, Object> paramMap);

    /* 회사별 프로그램 연결 조회 */
    List<ProgCompDto> getCompanyPrograms(Map<String, Object> paramMap);

    /* 회사-프로그램 연결 */
    int connectCompanyProgram(Map<String, Object> paramMap);

    /* 회사-프로그램 연결 해제 */
    int disconnectCompanyProgram(Map<String, Object> paramMap);

    /* 회사별 프로그램 목록 */
    List<ProgMenuDto> getProgMenuCompany(Map<String, Object> paramMap);
}
