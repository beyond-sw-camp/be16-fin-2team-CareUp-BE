package com.careup.ordering.ordering.product.entity;

import com.careup.ordering.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
// BaseTimeEntity 추가예정
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(name = "min_price",nullable = false)
    private Long minPrice;

    @Column(name = "max_price")
    private Long maxPrice;

    @Column(name = "image_url",nullable = false,length = 255)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductAttribute> attributes = new ArrayList<>();

    @Builder
    public Product(Category category, String name, String description,
                   Long minPrice, Long maxPrice, String imageUrl) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.imageUrl = imageUrl;
    }
}
