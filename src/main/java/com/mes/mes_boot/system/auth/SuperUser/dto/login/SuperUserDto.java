package com.mes.mes_boot.system.auth.SuperUser.dto.login;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuperUserDto {
    private String id; // 사용자 IDX
    private String userid; // 사용자 ID
    private String name; // 사용자 이름
    private String role; // 사용자 구분
    private List<String> permissions; // 사용자 권한
}
