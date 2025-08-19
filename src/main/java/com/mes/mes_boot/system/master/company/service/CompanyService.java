package com.mes.mes_boot.system.master.company.service;

import com.mes.mes_boot.system.master.company.dto.CompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanySaveDto;
import com.mes.mes_boot.system.master.company.dto.ResponseCompanySaveDto;
import com.mes.mes_boot.system.master.company.mapper.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CompanyService {
    private final CompanyMapper companyMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    /* 거래처 조회 */
    public List<CompanyDto> getCompanyList(RequestCompanyDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("company", request.getCompany());
            paramMap.put("coIdx", request.getCoIdx());
            paramMap.put("useYn", request.getUseYn());

            List<CompanyDto> companyList = companyMapper.getCompanyList(paramMap);

            // 각 거래처에 대해 coType 정보 조회 후 설정
            for (CompanyDto company : companyList) {
                List<String> coTypeList = companyMapper.getCompanyCoType(company.getCoIdx());
                company.setCoType(coTypeList.toArray(new String[0]));
            }

            return companyList;
        } catch (Exception e) {
            log.error("조회 중 오류 발생", e);
            return null;
        }
    }

    /* 거래처 저장 */
    @Transactional
    public ResponseCompanySaveDto saveCompany(RequestCompanySaveDto request) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("coCd", request.getCoCd());
            paramMap.put("coNm", request.getCoNm());
            paramMap.put("useYn", request.getUseYn());
            paramMap.put("compAddr", request.getCompAddr());
            paramMap.put("compType", request.getCompType());
            paramMap.put("compItem", request.getCompItem());
            paramMap.put("compCurr", request.getCompCurr());
            paramMap.put("bizNo", request.getBizNo());
            paramMap.put("ceoNm", request.getCeoNm());
            paramMap.put("tel", request.getTel());
            paramMap.put("fax", request.getFax());
            paramMap.put("email", request.getEmail());
            paramMap.put("country", request.getCountry());
            paramMap.put("userIdx", request.getUserIdx());

            // 신규 등록인지 확인
            boolean isNewComp = request.getCurCoIdx() == null || request.getCurCoIdx().trim().isEmpty();
            String coIdx;

            if (isNewComp) {
                // 중복 확인
                int duplicateCount = companyMapper.checkDuplicateCompCode(paramMap);
                if (duplicateCount > 0) {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("이미 존재하는 거래처코드입니다.")
                            .build();
                }

                // 다음 idx 조회
                Integer nextCompanyIdx = companyMapper.getNextCoIdx();
                if (nextCompanyIdx == null) {
                    nextCompanyIdx = 1;
                }
                coIdx = nextCompanyIdx.toString();

                // 신규 등록
                paramMap.put("coIdx", nextCompanyIdx);
                int insertResult = companyMapper.insertCompany(paramMap);

                if (insertResult <= 0) {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("데이터 저장 중 오류가 발생했습니다.")
                            .build();
                }

            } else {
                coIdx = request.getCurCoIdx();

                // 수정 모드 - 기존 데이터 조회
                paramMap.put("coIdx", coIdx);

                String existingCompanyCd = companyMapper.getCompCd(paramMap);
                if (existingCompanyCd == null) {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("기존 데이터 조회 중 오류가 발생했습니다.")
                            .build();
                }

                // 코드가 변경된 경우에만 중복 확인
                if (!existingCompanyCd.equals(request.getCoCd())) {
                    int duplicateCount = companyMapper.checkDuplicateCompCode(paramMap);
                    if (duplicateCount > 0) {
                        return ResponseCompanySaveDto.builder()
                                .success(false)
                                .error("이미 존재하는 코드입니다.")
                                .build();
                    }
                }

                // 거래처 수정
                int updateResult = companyMapper.updateCompany(paramMap);

                if (updateResult <= 0) {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("수정할 데이터를 찾을 수 없습니다.")
                            .build();
                }
            }

            // 거래처 유형 저장 (기존 데이터 삭제 후 재등록)
            saveCompanyCoType(coIdx, request.getCoType());

            return ResponseCompanySaveDto.builder()
                    .success(true)
                    .coIdx(coIdx)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCompanySaveDto.builder()
                    .success(false)
                    .error("저장 실패")
                    .build();
        }
    }

    /* 거래처 유형 저장 */
    private void saveCompanyCoType(String coIdx, String[] coTypes) {
        try {
            // 기존 거래처 유형 삭제
            companyMapper.deleteCompanyCoType(coIdx);

            // 새로운 거래처 유형 등록
            if (coTypes != null && coTypes.length > 0) {
                for (String coType : coTypes) {
                    if (coType != null && !coType.trim().isEmpty()) {
                        Map<String, Object> coTypeMap = new HashMap<>();
                        coTypeMap.put("coIdx", coIdx);
                        coTypeMap.put("value", coType.trim());
                        companyMapper.insertCompanyCoType(coTypeMap);
                    }
                }
            }
        } catch (Exception e) {
            log.error("거래처 유형 저장 중 오류 발생", e);
            throw e; // 트랜잭션 롤백을 위해 예외 재발생
        }
    }
}