package com.careup.ordering.ordering.product.entity;

import com.careup.ordering.ordering.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_list")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_list_id")
    private Long couponListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public CouponList(Coupon coupon, Member member) {
        this.coupon = coupon;
        this.member = member;
    }
}