package com.careup.branch.branch.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonDto {
    private Object result;
    private int statusCode;
    private String statusMessage;
}
