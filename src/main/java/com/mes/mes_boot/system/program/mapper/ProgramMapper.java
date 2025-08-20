package com.mes.mes_boot.system.program.mapper;

import com.mes.mes_boot.system.program.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
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

    /* 메뉴 목록 조회 */
    List<MenuDto> getMenuCategories();

    /* 메뉴 생성 */
    int createMenuCategory(Map<String, Object> paramMap);
    int updateMenuParentId(Map<String, Object> paramMap);

    /* 중메뉴 프로그램 연결 조회 */
    List<MenuProgDto> getMenuLinkPrograms(Map<String, Object> paramMap);

    /* 중메뉴 프로그램 연결 */
    int connectMenuProgram(Map<String, Object> paramMap);

    /* 중메뉴 프로그램 연결 해제 */
    int disconnectMenuProgram(Map<String, Object> paramMap);

    /* 메뉴별 연결된 프로그램 전체 조회 */
    List<ProgMenuDto> getMenuProg();

    /* 메뉴 프로그램 정렬순서 업데이트 */
    int updateMenuProgOrder(Map<String, Object> paramMap);
}
