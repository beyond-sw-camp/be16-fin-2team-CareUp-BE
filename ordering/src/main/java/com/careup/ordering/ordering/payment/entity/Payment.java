package com.careup.ordering.ordering.payment.entity;

import com.careup.ordering.ordering.order.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "amount",nullable = false)
    private Long amount;

    @Column(name = "payment_method",nullable = false)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status",nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "pg_transaction_id",length = 100)
    private String pgTransactionId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Payment(Order order,Long amount,String paymentMethod){
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.createdAt = LocalDateTime.now();
    }

    public void completePayment(String pgTransactionId){
        this.paymentStatus = PaymentStatus.COMPLETED;
        this.pgTransactionId = pgTransactionId;
    }

    public void failPayment(){
        this.paymentStatus = PaymentStatus.FAILED;
    }
}
