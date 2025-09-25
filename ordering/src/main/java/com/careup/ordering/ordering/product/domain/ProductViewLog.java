package com.careup.ordering.ordering.product.domain;

import com.careup.ordering.ordering.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_view_log")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_view_log_id")
    private Long productViewLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_product_id", nullable = false)
    private BranchProduct branchProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "repeat_purchase_rate", precision = 5,scale = 2)
    private BigDecimal repeatPurchaseRate;

    @Builder
    public ProductViewLog(BranchProduct branchProduct,Member member,
                          Long viewCount,BigDecimal repeatPurchaseRate){
        this.branchProduct = branchProduct;
        this.member = member;
        this.viewCount = viewCount;
        this.repeatPurchaseRate = repeatPurchaseRate;
    }
}
