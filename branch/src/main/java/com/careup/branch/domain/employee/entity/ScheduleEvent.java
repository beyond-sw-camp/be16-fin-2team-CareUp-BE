package com.careup.branch.domain.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEvent extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // 근태 기록 1:1 근태
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="schedule_id")
    private Schedule schedule;

    // 실제 날짜
    @Column(name = "event_date", nullable = true)
    private LocalDate eventDate;

    //실제 출근 시간
    @Column(name = "clock_in_at", nullable = true)
    private LocalDateTime clockInAt;

    //실제 휴게 시작
    @Column(name = "break_start_at", nullable = true)
    private LocalDateTime breakStartAt;

    //실제 휴게 종료
    @Column(name = "break_end_at", nullable = true)
    private LocalDateTime breakEndAt;

    //실제 퇴근 시간
    @Column(name = "clock_out_at", nullable = true)
    private LocalDateTime clockOutAt;

    //출근 위치
    @Column(name = "clock_in_location", nullable = true, columnDefinition = "POINT SRID 4326")
    private Point clockIntLocation;

    //퇴근 위치
    @Column(name = "clock_out_location", nullable = true)
    private Point clockOutLocation;

}
