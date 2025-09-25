package com.careup.branch.domain.branch.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class KPI extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //KPI 1:N 지점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="branch_id")
    private Branch branch;

    //속성명
    @Column(length = 20, nullable = false)
    private String name;

    //목표량
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal goal;

    //진행량
    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal progress;
}
