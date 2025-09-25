package com.careup.ordering.ordering.product.domain;

import com.careup.ordering.ordering.order.domain.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch_products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BranchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_products_id")
    private Long branchProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "serial_number",nullable = false, length = 100)
    private String serialNumber;

    @Column(name = "stock_quantity",nullable = false)
    private Long stockQuantity;

    @Column(name = "safety_stock",nullable = false)
    private Long safetystock = 0L;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToMany(mappedBy = "branchProduct", cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "branchProduct", cascade = CascadeType.ALL)
    private List<InventoryFlowDetail> inventoryFlows = new ArrayList<>();

    @Builder
    private BranchProduct(Product product, Long branchId, String serialNumber,
                          Long stockQuantity,Long safetystock,Long price){
        this.product = product;
        this.branchId = branchId;
        this.serialNumber = serialNumber;
        this.stockQuantity =stockQuantity;
        this.safetystock = safetystock;
        this.price =price;
    }

    public void decreaseStock(Long quantity) {
        if (this.stockQuantity < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stockQuantity -= quantity;
    }

    // 재고 증가
    public void increaseStock(Long quantity) {
        this.stockQuantity += quantity;
    }

}
