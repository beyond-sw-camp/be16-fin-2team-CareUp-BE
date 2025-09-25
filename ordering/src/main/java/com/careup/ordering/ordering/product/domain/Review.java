package com.careup.ordering.ordering.product.domain;

import com.careup.ordering.common.domain.BaseTimeEntity;
import com.careup.ordering.ordering.member.domain.Member;
import com.careup.ordering.ordering.order.domain.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "rate", precision = 2, scale = 1)
    private BigDecimal rate = BigDecimal.ZERO;

    @Builder
    public Review(Order order, Member member, String content, BigDecimal rate) {
        this.order = order;
        this.member = member;
        this.content = content;
        this.rate = rate;
    }
}