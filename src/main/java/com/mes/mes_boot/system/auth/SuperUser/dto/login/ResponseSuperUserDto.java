package com.mes.mes_boot.system.auth.SuperUser.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // Builder 패턴 사용 가능하게 함 (객체 생성시 .builder().field(value).build())
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSuperUserDto {
    private boolean success;
    private String message;
    private String accessToken;
    private String refreshToken;
    private SuperUserDto user;
}
