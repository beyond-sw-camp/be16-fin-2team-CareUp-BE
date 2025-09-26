package com.careup.ordering.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchProductRequestDto {
    @NotNull(message = "상품 ID는 필수입니다")
    private Long productId;

    @NotNull(message = "지점 ID는 필수입니다")
    private Long branchId;

    @NotBlank(message = "시리얼 번호는 필수입니다")
    private String serialNumber;

    @NotNull(message = "재고 수량은 필수입니다")
    @PositiveOrZero(message = "재고 수량은 0 이상이어야 합니다")
    private Long stockQuantity;

    @PositiveOrZero(message = "안전 재고는 0 이상이어야 합니다")
    private Long safetyStock = 0L;

    @NotNull(message = "가격은 필수입니다")
    @PositiveOrZero(message = "가격은 0 이상이어야 합니다")
    private Long price;

    private BranchProduct.BranchProductType branchProductsType;
}