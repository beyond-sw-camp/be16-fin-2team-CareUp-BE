package com.careup.ordering.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CouponStatus status = CouponStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CouponType type;

    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "vaild_from", nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_to", nullable = false)
    private LocalDateTime validTo;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Builder
    public Coupon(String name, String description, CouponType type,
                  BigDecimal percentage, Long amount, LocalDateTime validFrom, LocalDateTime validTo) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.percentage = percentage;
        this.amount = amount;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
}
