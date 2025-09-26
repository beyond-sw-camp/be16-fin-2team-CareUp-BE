package com.careup.ordering.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductAttributeRequestDto {
    @NotBlank(message = "속성명은 필수입니다")
    private String attributeName;

    @NotBlank(message = "속성값을 필수입니다")
    private String attributeValue;
}
