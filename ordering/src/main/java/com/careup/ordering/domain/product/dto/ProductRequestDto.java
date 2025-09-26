package com.careup.ordering.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequestDto {
    @NotNull(message = "카테고리는 필수입니다.")
    private Long categoryId;

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotBlank(message = "상품 설명은 필수입니다")
    private String description;

    @NotNull(message = "최소 가격은 필수입니다")
    @Positive(message = "최소 가격은 0보다 커야 합니다")
    private Long minPrice;

    private Long maxPrice;

    @NotBlank(message = "이미지 URL은 필수입니다")
    private String imageUrl;

    private List<ProductAttributeRequestDto> attributes;
}
