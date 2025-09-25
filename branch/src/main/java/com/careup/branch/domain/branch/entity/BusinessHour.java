package com.careup.branch.domain.branch.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessHour extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // 지점 운영 시간 N:1 지점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="branch_id")
    private Branch branch;

    //날짜 분류
    private BusinessHourType businessHourType;

    //시작 시간
    @Column(name = "open_time", nullable = false)
    private LocalDateTime openTime;

    //종료 시간
    @Column(name = "close_time", nullable = false)
    private LocalDateTime closeTime;

    //휴무 여부
    @Column(name="is_closed", nullable = false)
    private Boolean isClosed;
}
