package com.careup.branch.domain.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import com.careup.branch.domain.branch.entity.Branch;
import com.careup.branch.domain.chat.entity.ChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DispatchStatus extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //배치 현황 N:1 직원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="employee_id")
    private Employee employee;

    //배치 현황 N:1 지점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="branch_id")
    private Branch branch;

    //배치 시작일
    @Column(name = "assigned_from", nullable = false)
    private LocalDate assignedFrom;

    //배치 종료일
    @Column(name = "assigned_to", nullable = false)
    private LocalDate assignedTo;

    //배치 취소 일자
    @Column(name = "displacement_date", nullable = false)
    private LocalDate displacementDAte;

    //배치 취소
    @Column(name = "placement_Yn", nullable = false)
    @Builder.Default
    private String placementYn = "N";

}
