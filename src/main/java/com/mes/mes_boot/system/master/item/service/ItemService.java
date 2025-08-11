package com.mes.mes_boot.system.master.item.service;

import com.mes.mes_boot.system.master.item.dto.ItemDto;
import com.mes.mes_boot.system.master.item.dto.RequestItemDto;
import com.mes.mes_boot.system.master.item.dto.RequestItemSaveDto;
import com.mes.mes_boot.system.master.item.dto.ResponseItemSaveDto;
import com.mes.mes_boot.system.master.item.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    private final ItemMapper itemMapper;
    Map<String, Object> paramMap = new HashMap<>();

    @Autowired
    public ItemService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    /* 품목 조회 */
    public List<ItemDto> getItemList(RequestItemDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("item", request.getItem());
            paramMap.put("itemIdx", request.getItemIdx());
            paramMap.put("itemType", request.getItemType());
            paramMap.put("itemYn", request.getItemYn());

            return itemMapper.getItemList(paramMap);
        } catch (Exception e) {
            return null;
        }
    }

    /* 품목 저장 */
    public ResponseItemSaveDto saveItem(RequestItemSaveDto request) {
        try {
            paramMap = new HashMap<>();
            paramMap.put("companyIdx", request.getCompanyIdx());
            paramMap.put("itemCd", request.getItemCd());
            paramMap.put("itemNm", request.getItemNm());
            paramMap.put("itemSpec", request.getItemSpec());
            paramMap.put("itemType", request.getItemType());
            paramMap.put("itemUnit", request.getItemUnit());
            paramMap.put("useYn", request.getUseYn());
            paramMap.put("etc", request.getEtc());

            // 신규 등록인지 확인 (curItemIdx가 비어있으면 신규)
            boolean isNewItem = request.getCurItemIdx() == null || request.getCurItemIdx().trim().isEmpty();

            if (isNewItem) {
                // 중복 품목코드 확인
                int duplicateCount = itemMapper.checkDuplicateItemCode(paramMap);
                if (duplicateCount > 0) {
                    return ResponseItemSaveDto.builder()
                            .success(false)
                            .error("이미 존재하는 품목코드입니다.")
                            .build();
                }

                // 다음 item_idx 조회
                Integer nextItemIdx = itemMapper.getNextItemIdx();
                if (nextItemIdx == null) {
                    nextItemIdx = 1;
                }

                // 신규 품목 등록
                paramMap.put("itemIdx", nextItemIdx);
                int insertResult = itemMapper.insertItem(paramMap);

                if (insertResult > 0) {
                    return ResponseItemSaveDto.builder()
                            .success(true)
                            .itemIdx(nextItemIdx.toString())
                            .build();
                } else {
                    return ResponseItemSaveDto.builder()
                            .success(false)
                            .error("데이터 저장 중 오류가 발생했습니다.")
                            .build();
                }

            } else {
                // 수정 모드 - 기존 데이터 조회
                paramMap.put("itemIdx", request.getCurItemIdx());

                String existingItemCd = itemMapper.getItemCd(paramMap);
                if (existingItemCd == null) {
                    return ResponseItemSaveDto.builder()
                            .success(false)
                            .error("기존 데이터 조회 중 오류가 발생했습니다.")
                            .build();
                }

                // 품목코드가 변경된 경우에만 중복 확인
                if (!existingItemCd.equals(request.getItemCd())) {
                    int duplicateCount = itemMapper.checkDuplicateItemCode(paramMap);
                    if (duplicateCount > 0) {
                        return ResponseItemSaveDto.builder()
                                .success(false)
                                .error("이미 존재하는 품목코드입니다.")
                                .build();
                    }
                }

                // 품목 수정
                int updateResult = itemMapper.updateItem(paramMap);

                if (updateResult > 0) {
                    return ResponseItemSaveDto.builder()
                            .success(true)
                            .itemIdx(request.getCurItemIdx())
                            .build();
                } else {
                    return ResponseItemSaveDto.builder()
                            .success(false)
                            .error("수정할 데이터를 찾을 수 없습니다.")
                            .build();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseItemSaveDto.builder()
                    .success(false)
                    .error("저장 실패")
                    .build();
        }
    }
}