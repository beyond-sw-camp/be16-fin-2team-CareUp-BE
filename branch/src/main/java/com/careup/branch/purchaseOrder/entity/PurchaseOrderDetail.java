package com.careup.branch.purchaseOrder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_detail_id", nullable = false)
    private long purchaseOrderDetailId;

    // 발주와 1:N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "approved_quantity", nullable = false)
    private int approvedQuantity;

    @Column(name = "subtotal_price", nullable = false)
    private long subtotalPrice;

    @Column(name = "unit_price", nullable = false)
    private long unitPrice;

    // 상품 ID
    @Column(name = "product_id", nullable = false)
    private long productId;
}
