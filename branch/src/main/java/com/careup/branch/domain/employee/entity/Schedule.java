package com.careup.branch.domain.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import com.careup.branch.domain.chat.entity.ChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //근태 N:1 직원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="employee_id")
    private Employee employee;

    // 근태 1:1 근태 종류
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="schedule_type_id")
    private ScheduleType scheduleType;

    // 근태 N:1 근태 템플릿
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="schedule_template_id")
    private AttendanceTemplate attendanceTemplate;

    //날짜
    @Column(name = "registered_date", nullable = true)
    private LocalDate registeredDate;

    //등록된 출근시간
    @Column(name = "registered_clock_id", nullable = true)
    private LocalDateTime registeredClockIn;

    //등록된 휴게 시작 시간
    @Column(name = "registered_break_start", nullable = true)
    private LocalDateTime registeredBreakStart;

    //등록된 휴게 종료 시간
    @Column(name = "registered_break_end", nullable = true)
    private LocalDateTime registeredBreakEnd;

    //등록된 퇴근 시간
    @Column(name = "registered_clock_out", nullable = true)
    private LocalDateTime registeredClockOut ;
}