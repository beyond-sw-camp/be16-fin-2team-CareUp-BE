package com.careup.ordering.member.entity;


import com.careup.ordering.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email",nullable = false,unique = true,length = 50)
    private String email;

    @Column(name = "pasword", nullable = false, length = 100)
    private String password;

    @Column(name = "nickname",nullable = false, unique = true,length = 10)
    private String nickname;

    @Column(name = "name", nullable = false , length = 30)
    private String name;

    @Column(name= "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "phone" , length = 12)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "is_del_yn", nullable = false, length = 1)
    private String isDelYn = "N";

    @Builder
    public Member(String email, String password, String nickname, String name,
                  LocalDate birthday, String phone, Gender gender) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
    }
}
