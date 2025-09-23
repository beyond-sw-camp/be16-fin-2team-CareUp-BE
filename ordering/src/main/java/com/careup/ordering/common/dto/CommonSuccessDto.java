package com.careup.ordering.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonSuccessDto {
    private Object result;
    private int status_code;
    private String status_message;
}
