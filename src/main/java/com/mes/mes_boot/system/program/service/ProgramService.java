package com.mes.mes_boot.system.program.service;

import com.mes.mes_boot.system.program.dto.*;
import com.mes.mes_boot.system.program.mapper.ProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramService {
    private final ProgramMapper programMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public ProgramService(ProgramMapper programMapper) {
        this.programMapper = programMapper;
    }

    /* 프로그램 전체 조회 */
    public List<ProgramDto> getPrograms() {
        try {
            return programMapper.getPrograms();
        } catch (Exception e) {
            return null;
        }
    }

    /* 프로그램 추가 */
    public boolean createProgram(RequestProgDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("progName", request.getProgName());
            paramMap.put("path", request.getPath());
            paramMap.put("description", request.getDescription());

            int result = programMapper.createProgram(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 프로그램 삭제 */
    public boolean deleteProgram(RequestProgDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("programId", request.getProgramId());

            int result = programMapper.deleteProgram(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 프로그램 수정 */
    public boolean updateProgram(RequestProgDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("programId", request.getProgramId());
            paramMap.put("progName", request.getProgName());
            paramMap.put("path", request.getPath());
            paramMap.put("description", request.getDescription());

            int result = programMapper.updateProgram(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 회사별 프로그램 연결 조회 */
    public List<ProgCompDto> getCompanyPrograms(String companyCode) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyCode", companyCode);

            return programMapper.getCompanyPrograms(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 회사-프로그램 연결 */
    public boolean connectCompanyProgram(RequestConnectCompProgDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyCode", request.getCompanyCode());
            paramMap.put("programId", request.getProgramId());

            int result = programMapper.connectCompanyProgram(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 회사-프로그램 연결 해제 */
    public boolean disconnectCompanyProgram(RequestConnectCompProgDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyCode", request.getCompanyCode());
            paramMap.put("programId", request.getProgramId());

            int result = programMapper.disconnectCompanyProgram(paramMap);
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* 회사별 프로그램 목록 */
    public List<ProgMenuDto> getProgMenuCompany(String company_idx) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("company_idx", company_idx);

            return programMapper.getProgMenuCompany(paramMap);
        } catch (Exception e) {
            return null;
        }
    }
}
