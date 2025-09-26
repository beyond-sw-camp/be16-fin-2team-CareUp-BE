package com.careup.branch.domain.branch.entity;


import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Branch extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String code; // 지점 코드

    @Column(length = 100, nullable = false)
    private String name; // 지점명

    @Column(length = 30, nullable = false)
    private String businessDomain; // 업종

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OwnershipType ownershipType; // 직영 여부

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BranchStatus status = BranchStatus.OPENED; // 지점 상태(OPENED, CLOSED, SUSPENDED))

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate openDate; // 개업연월

    @Column(length = 20, unique = true, nullable = false)
    private String businessNumber; // 사업자등록번호

    @Column(length = 20, unique = true, nullable = false)
    private String corporationNumber; // 법인등록번호

    @Column(length = 20, nullable = false)
    private String zipcode; // 지점 우편번호

    @Column(nullable = false)
    private String address; // 지점 주소

    private String addressDetail; // 지점 상세 주소

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl; // 지점 프로필 이미지

    @Column(length = 32, nullable = false)
    private String phone; // 지점 전화번호

    @Column(nullable = false)
    private String email; // 지점 이메일

    @Column(nullable = false)
    private String location; // 지정된 출퇴근 위치

    @Column(nullable = false)
    private Integer geofenceRadius; // 출퇴근 가능 반경

    @Column(length = 200)
    private String remark; // 비고
}
