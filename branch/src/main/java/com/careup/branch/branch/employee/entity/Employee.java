package com.careup.branch.branch.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Employee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @Max(value = 30)
    @Column(unique = true)
    private String employeeNumber; // 사원번호

    @Max(value = 100)
    private String name; // 성명

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth; // 생년월일

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별 (MALE(남성), FEMALE(여성))

    @Column(unique = true)
    private String email; // 이메일

    @Max(value = 20)
    private String zipcode; // 우편번호

    private String address; // 주소

    private String addressDetail; // 상세 주소

    @Max(value = 32)
    private String mobile; // 전화번호

    @Max(value = 32)
    private String emergencyTel; // 비상 연락망

    @Enumerated(EnumType.STRING)
    private Relationship relationship; // 관계 ()

    @Temporal(TemporalType.DATE)
    private LocalDate hireDate; // 입사일

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate terminateDate; // 퇴사일

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus; // 고용 상태 (ACTIVE, ON_LEAVE, TERMINATED)

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType; // 고용 형태 ()

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl; // 프로필 사진

    @Max(value = 200)
    @Column(nullable = false)
    private String remark; // 비고
}
