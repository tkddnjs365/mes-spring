package com.mes.mes_boot.system.auth.User.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String id; // 사용자 IDX
    private String company_idx; //회사 ID
    private String user_id; // 사용자 ID
    private String name; // 사용자 이름
    private String role; // 사용자 구분
    private List<String> permissions; // 사용자 권한
    private Boolean isApproved;
    private String createdAt;
    private String updatedAt;
}
