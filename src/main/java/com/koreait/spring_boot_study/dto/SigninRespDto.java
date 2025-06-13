package com.koreait.spring_boot_study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SigninRespDto {
    private String status;
    private String message;
}
