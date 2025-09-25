package com.careup.ordering.ordering.order.domain;

import com.careup.ordering.ordering.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordered")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_item_id")
    private Long orderedItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity",nullable = false)
    private Long quantity;

    @Column(name = "unit_price", nullable = false)
    private Long unitPrice;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Builder
    public OrderedItem(Order order,Product product,Long quantity,Long unitPrice){
     this.order = order;
     this.product = product;
     this.quantity = quantity;
     this.unitPrice = unitPrice;
     this.totalPrice = quantity * unitPrice;
    }
}
