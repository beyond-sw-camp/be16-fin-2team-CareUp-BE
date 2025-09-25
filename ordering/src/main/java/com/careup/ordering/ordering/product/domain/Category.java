package com.careup.ordering.ordering.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name",nullable = false,length = 255)
    private String name;

    @Column(name = "description",length = 255)
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Builder
    private Category(String name, String description){
        this.name= name;
        this.description = description;
    }
}
