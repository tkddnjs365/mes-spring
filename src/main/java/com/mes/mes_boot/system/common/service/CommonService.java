package com.mes.mes_boot.system.common.service;

import com.mes.mes_boot.system.common.dto.CommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonDto;
import com.mes.mes_boot.system.common.dto.RequestCommonSaveDto;
import com.mes.mes_boot.system.common.mapper.CommonMapper;
import com.mes.mes_boot.system.program.dto.RequestProgDto;
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

    /* 회사별 공통데이터 그룹별 데이터 조회 */
    public List<CommonDto> getCompGroupDtl(RequestCommonDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("groupId", request.getGroupId());
            paramMap.put("companyIdx", request.getCompanyIdx());

            return commonMapper.getCompGroupDtl(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 공통코드 추가 */
    public boolean insertCommon(RequestCommonSaveDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("groupId", request.getGroupId());
            paramMap.put("value", request.getValue());
            paramMap.put("sortOrder", request.getSortOrder());
            paramMap.put("useYn", request.getUseYn());
            paramMap.put("keyId", request.getKeyId());

            int result = commonMapper.insertCommon(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 공통코드 삭제 */
    public boolean deleteCommon(RequestCommonSaveDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("dataId", request.getDataId());

            int result = commonMapper.deleteCommon(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 공통코드 수정 */
    public boolean updateCommon(RequestCommonSaveDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("dataId", request.getDataId());
            paramMap.put("value", request.getValue());
            paramMap.put("sortOrder", request.getSortOrder());
            paramMap.put("useYn", request.getUseYn());

            int result = commonMapper.updateCommon(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
