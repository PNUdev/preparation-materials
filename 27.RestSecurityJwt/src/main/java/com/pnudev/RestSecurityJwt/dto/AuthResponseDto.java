package com.pnudev.RestSecurityJwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AuthResponseDto {

    @Getter
    private final String jwt;
}
