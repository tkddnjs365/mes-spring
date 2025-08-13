package com.mes.mes_boot.system.common.mapper;


import com.mes.mes_boot.system.common.dto.CommonDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {
    /* 공통데이터 조회 */
    List<CommonDto> getCommon(Map<String, Object> paramMap);

    /* 회사별 공통데이터 그룹별 데이터 조회 */
    List<CommonDto> getCompGroupDtl(Map<String, Object> paramMap);

    /* 공통코드 추가 */
    int insertCommon(Map<String, Object> paramMap);

    /* 공통코드 삭제 */
    int deleteCommon(Map<String, Object> paramMap);

    /* 공통코드 수정 */
    int updateCommon(Map<String, Object> paramMap);
}
