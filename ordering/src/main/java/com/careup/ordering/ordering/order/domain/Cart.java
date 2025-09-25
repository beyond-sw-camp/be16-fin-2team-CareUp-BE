package com.careup.ordering.ordering.order.domain;

import com.careup.ordering.ordering.member.domain.Member;
import com.careup.ordering.ordering.product.domain.BranchProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_products_id", nullable = false)
    private BranchProduct branchProduct;

    @Column(name = "quantity",nullable = false)
    private Long quantity;

    @Column(name = "attribute_name",length = 255)
    private String attributeName;

    @Column(name = "attribute_value",length = 255)
    private String attributeValue;

    @Builder
    public Cart(Member member,BranchProduct branchProduct,Long quantity,String attributeName,String attributeValue){
        this.member = member;
        this.branchProduct =branchProduct;
        this.quantity = quantity;
        this.attributeName =attributeName;
        this.attributeValue = attributeValue;
    }
}
