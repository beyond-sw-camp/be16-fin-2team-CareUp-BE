package com.careup.ordering.ordering.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_flow_detail")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryFlowDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_flow_detail_id")
    private Long inventoryFlowDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_products_id",nullable = false)
    private BranchProduct branchProduct;

    @Column(name = "`in`")  // 백틱으로 감싸기
    private Long inQuantity;

    @Column(name = "`out`")  // 백틱으로 감싸기
    private Long outQuantity;

    @Column(name = "create_at",nullable = false)
    private LocalDateTime createAt;

    @Column(name = "remark", length = 255)
    private String remark;

    @Builder
    public InventoryFlowDetail(BranchProduct branchProduct,Long inQuantity,Long outQuantity,
                               String remark){
        this.branchProduct = branchProduct;
        this.inQuantity = inQuantity;
        this.outQuantity = outQuantity;
        this.remark =remark;
        this.createAt = LocalDateTime.now();
    }
}
