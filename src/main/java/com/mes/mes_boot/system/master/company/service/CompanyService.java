package com.mes.mes_boot.system.master.company.service;

import com.mes.mes_boot.system.master.company.dto.CompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanyDto;
import com.mes.mes_boot.system.master.company.dto.RequestCompanySaveDto;
import com.mes.mes_boot.system.master.company.dto.ResponseCompanySaveDto;
import com.mes.mes_boot.system.master.company.mapper.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /* 품목 조회 */
    public List<CompanyDto> getCompanyList(RequestCompanyDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("company", request.getCompany());
            paramMap.put("coIdx", request.getCoIdx());
            paramMap.put("useYn", request.getUseYn());

            return companyMapper.getCompanyList(paramMap);
        } catch (Exception e) {
            log.error("조회 중 오류 발생", e);
            return null;
        }
    }

    /* 품목 저장 */
    public ResponseCompanySaveDto saveCompany(RequestCompanySaveDto request) {
        try {
            paramMap = new HashMap<>();
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

                // 신규 등록
                paramMap.put("coIdx", nextCompanyIdx);
                int insertResult = companyMapper.insertCompany(paramMap);

                if (insertResult > 0) {
                    return ResponseCompanySaveDto.builder()
                            .success(true)
                            .coIdx(nextCompanyIdx.toString())
                            .build();
                } else {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("데이터 저장 중 오류가 발생했습니다.")
                            .build();
                }

            } else {
                // 수정 모드 - 기존 데이터 조회
                paramMap.put("coIdx", request.getCurCoIdx());

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

                // 품목 수정
                int updateResult = companyMapper.updateCompany(paramMap);

                if (updateResult > 0) {
                    return ResponseCompanySaveDto.builder()
                            .success(true)
                            .coIdx(request.getCurCoIdx())
                            .build();
                } else {
                    return ResponseCompanySaveDto.builder()
                            .success(false)
                            .error("수정할 데이터를 찾을 수 없습니다.")
                            .build();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCompanySaveDto.builder()
                    .success(false)
                    .error("저장 실패")
                    .build();
        }
    }
}