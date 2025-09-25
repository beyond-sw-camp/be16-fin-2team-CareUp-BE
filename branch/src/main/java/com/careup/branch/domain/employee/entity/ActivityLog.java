package com.careup.branch.domain.employee.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
import com.careup.branch.domain.chat.entity.ChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // 직원 1:N 활동로그
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="employee_id")
    private Employee employee;
    //내용
    @Column(name = "log_contents", columnDefinition = "TEXT", nullable = false)
    private String logContents;
    //활동 페이지
    @Column(name="page_name", nullable = false, length = 50 )
    private String pageName;
    //활동 종류
    @Column(name="page_type", nullable = false,length = 50)
    private String pageType;

}
