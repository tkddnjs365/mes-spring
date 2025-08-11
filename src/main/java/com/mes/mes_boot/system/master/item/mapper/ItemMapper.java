package com.mes.mes_boot.system.master.item.mapper;

import com.mes.mes_boot.system.master.item.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemMapper {
    /* 품목 조회 */
    List<ItemDto> getItemList(Map<String, Object> paramMap);

    /* 품목코드 중복 확인 */
    int checkDuplicateItemCode(Map<String, Object> paramMap);

    /* 다음 item_idx 조회 */
    Integer getNextItemIdx();

    /* 품목 등록 */
    int insertItem(Map<String, Object> paramMap);

    /* 품목코드 조회 */
    String getItemCd(Map<String, Object> paramMap);

    /* 품목 수정 */
    int updateItem(Map<String, Object> paramMap);
}