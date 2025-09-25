package com.careup.ordering.ordering.order.entity;

import com.careup.ordering.ordering.member.entity.Member;
import com.careup.ordering.ordering.payment.entity.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "branch_id",nullable = false)
    private Long branchId;

    @Column(name = "total_amount",nullable = false)
    private Long totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column(name = "order_type", nullable = false)
    private OrderType orderType = OrderType.ONLINE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderedItem> orderedItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @Builder
    public Order(Member member, Long branchId, Long totalAmount, OrderType orderType) {
        this.member = member;
        this.branchId = branchId;
        this.totalAmount = totalAmount;
        this.orderType = orderType;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(OrderStatus status){
        this.orderStatus = status;
    }
}
