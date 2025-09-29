package com.careup.branch.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long attributeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "attribute_name", nullable = false, length = 50)
    private String attributeName; // 색상, 사이즈 등

    @Column(name = "attribute_value", nullable = false, length = 100)
    private String attributeValue; // 빨강, L 등

    @Builder
    public ProductAttribute(Product product, String attributeName, String attributeValue) {
        this.product = product;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
}