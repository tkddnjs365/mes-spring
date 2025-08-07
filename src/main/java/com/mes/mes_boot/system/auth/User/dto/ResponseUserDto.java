package com.mes.mes_boot.system.auth.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {
    private boolean success;
    private String message;
    private String accessToken;
    private String refreshToken;
    private UserDto user;
}
