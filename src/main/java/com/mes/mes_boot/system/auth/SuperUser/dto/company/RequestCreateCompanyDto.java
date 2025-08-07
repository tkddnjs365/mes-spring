package com.mes.mes_boot.system.auth.SuperUser.dto.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateCompanyDto {
    @NotBlank(message = "회사 코드는 필수입니다.")
    private String code;      // 회사 코드

    @NotBlank(message = "회사명은 필수입니다.")
    private String name;      // 회사명

    private String description;
}
