package com.careup.ordering.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductAttributeResponseDto {
    private Long attributeId;
    private String attributeName;
    private String attributeValue;
}