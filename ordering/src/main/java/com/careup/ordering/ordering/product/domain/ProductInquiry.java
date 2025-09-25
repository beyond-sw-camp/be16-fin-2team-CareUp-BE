package com.careup.ordering.ordering.product.domain;

import com.careup.ordering.common.domain.BaseTimeEntity;
import com.careup.ordering.ordering.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_inquiry")
public class ProductInquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_products_id", nullable = false)
    private BranchProduct branchProduct;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "inquiry_type", nullable = false)
    private InquiryType inquiryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InquiryStatus status = InquiryStatus.PENDING;

    @Column(name = "is_secret", nullable = false)
    private Boolean isSecret;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    private List<ProductInquiryAnswer> answers = new ArrayList<>();

    @Builder
    public ProductInquiry(Member member, BranchProduct branchProduct, String title,
                          String content, InquiryType inquiryType, Boolean isSecret) {
        this.member = member;
        this.branchProduct = branchProduct;
        this.title = title;
        this.content = content;
        this.inquiryType = inquiryType;
        this.isSecret = isSecret;
    }
}
