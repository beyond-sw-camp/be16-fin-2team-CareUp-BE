package com.careup.branch.branch.owners.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import com.careup.branch.branch.employee.entity.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owners extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String ownersNo; // 가맹점주 번호

    @Column(length = 100, nullable = false)
    private String name; // 성명

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birth; // 생년월일

    @Column(length = 20, nullable = false)
    private String bizNo; // 사업자등록번호

    @Column(length = 20, nullable = false)
    private String corpNo; // 법인등록번호

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    @Column(unique = true, nullable = false)
    private String email; // 이메일

    @Column(length = 20, nullable = false)
    private String zipcode; // 우편번호

    @Column(length = 100, nullable = false)
    private String address; // 주소

    @Column(length = 100, nullable = false)
    private String addressDetail; // 상세 주소

    @Column(length = 32, unique = true, nullable = false)
    private String mobile; // 휴대폰

    @Column(length = 100, nullable = false)
    private String attorney_name; // 대리인명

    @Column(length = 32, nullable = false)
    private String attorneyPhoneNumber; // 대리인 연라겇

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl; // 프로필 사진

    @Column(length = 200)
    private String remark; // 비고
}
