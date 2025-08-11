package com.mes.mes_boot.system.common.service;

import com.mes.mes_boot.system.common.dto.CommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonDto;
import com.mes.mes_boot.system.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    private final CommonMapper commonMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public CommonService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    /* 공통데이터 조회 */
    public List<CommonDto> getCommon(RequestCommonDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("groupId", request.getGroupId());
            paramMap.put("useYn", request.getUseYn());
            paramMap.put("companyIdx", request.getCompanyIdx());

            return commonMapper.getCommon(paramMap);
        } catch (Exception e) {
            return null;
        }
    }
}
