package com.careup.ordering.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {
    private Long categoryId;
    private String name;
    private String description;
    private Long productCount;
}
