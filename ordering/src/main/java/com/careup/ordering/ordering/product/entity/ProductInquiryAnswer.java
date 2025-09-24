package com.careup.ordering.ordering.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInquiryAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id",nullable = false)
    private ProductInquiry inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_product_id", nullable = false)
    private BranchProduct branchProduct;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "employee_id")
    private String employeeId; // Branch 서비스의 Employee 참조

    @Builder
    public ProductInquiryAnswer(ProductInquiry inquiry, BranchProduct branchProduct,
                                String content, String employeeId) {
        this.inquiry = inquiry;
        this.branchProduct = branchProduct;
        this.content = content;
        this.employeeId = employeeId;
    }
}
