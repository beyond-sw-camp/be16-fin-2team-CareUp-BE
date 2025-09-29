package com.careup.branch.domain.branch.entity;

import com.careup.branch.common.domain.BaseTimeEntity;
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
public class Documents extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //서류 1:N 지점
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="branch_id")
    private Branch branch;

    //계약 분류
    private DocumentsType documentsType;

    //서류
    @Column(name="document_url", columnDefinition = "TEXT")
    private String documentUrl;

}
