package com.careup.ordering.domain.product.dto;

import com.careup.ordering.domain.product.entity.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProductResponseDto {
    private Long productId;
    private String categoryName;
    private String name;
    private String description;
    private Long minPrice;
    private Long maxPrice;
    private String imageUrl;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ProductAttributeResponseDto> attributes;
}
