package com.careup.branch.domain.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Column(length = 30, unique = true, nullable = false)
    private String employeeNumber; // 사원번호

    @Column(length = 100, nullable = false)
    private String name; // 성명

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth; // 생년월일

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별 (MALE(남성), FEMALE(여성))

    @Column(unique = true, nullable = false)
    private String email; // 이메일

    @Column(length = 20, nullable = false)
    private String zipcode; // 우편번호

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private String addressDetail; // 상세 주소

    @Column(length = 32, nullable = false)
    private String mobile; // 전화번호

    @Column(length = 32, nullable = false)
    private String emergencyTel; // 비상 연락망

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Relationship relationship; // 관계 ()

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate hireDate; // 입사일

    @Temporal(TemporalType.DATE)
    private LocalDate terminateDate; // 퇴사일

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus; // 고용 상태 (ACTIVE, ON_LEAVE, TERMINATED)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType; // 고용 형태 ()

    @Column(columnDefinition = "TEXT", nullable = false)
    private String profileImageUrl; // 프로필 사진

    @Column(length = 200)
    private String remark; // 비고
}
