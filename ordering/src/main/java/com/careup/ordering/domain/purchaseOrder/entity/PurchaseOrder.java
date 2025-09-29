package com.careup.ordering.domain.purchaseOrder.entity;

import com.careup.ordering.common.domain.BaseTimeEntity;
import com.careup.ordering.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id", nullable = false)
    private long id;

    @JoinColumn(name = "branch_id", nullable = false)
    private long branchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //총 금액
    @Column(name= "total_amount", nullable = false)
    private Long totalAmount;

    //승인자
    @Column(name= "approved_by", nullable = true)
    private Long approvedBy;

    //승인 시간
    @Column(name = "approved_at", nullable = true)
    private LocalDateTime approvedAt;

    //거부 사유
    @Column(name = "rejected_reason", nullable = true)
    private String rejectedReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;
}
