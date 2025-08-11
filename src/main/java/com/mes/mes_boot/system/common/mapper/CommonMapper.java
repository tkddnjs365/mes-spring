package com.mes.mes_boot.system.common.mapper;


import com.mes.mes_boot.system.common.dto.CommonDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {
    /* 공통데이터 조회 */
    List<CommonDto> getCommon(Map<String, Object> paramMap);
}
