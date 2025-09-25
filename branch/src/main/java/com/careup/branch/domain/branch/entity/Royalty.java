package com.careup.branch.domain.branch.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Royalty extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // 로얄티 1:1 지점
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="branch_id")
    private Branch branch;

    //로열티 산정 기준
    private CalculationMethod calculationMethod;

    //매출액 비율
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal percentage;

    //고정 금액
    @Column(name = "fixed_amount", nullable = true)
    private Long fixedAmount;

    //적용 기준 연월
    @Column(name="applicable_month", length = 6, nullable = false)
    private String applicableMonth;

    //최종 로열티 금액
    @Column(nullable = false)
    private Long amount;

    //납부 기한
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    //정산 상태
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "settlement_status", nullable = false)
    private SettlementStatus settlementStatus = SettlementStatus.PENDING;

    //실제 입금 날짜
    @Column(name = "payment_date", nullable = true)
    private LocalDateTime paymentDate;

}
