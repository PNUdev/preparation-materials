package com.ihorpolataiko.springbootdemoforpnudev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormData {

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

}
