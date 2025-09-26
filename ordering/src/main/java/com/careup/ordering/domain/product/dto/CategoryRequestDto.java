package com.careup.ordering.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRequestDto {
    @NotBlank(message = "카테고리명은 필수입니다")
    private String name;
    private String description;
}
