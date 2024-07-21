package org.example.reservationpurchaseapi.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.reservationpurchaseapi.infrastructure.redis.dto.OrderIssueRequest;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_number", updatable = false)
    private Long id;

    @Column(name = "product_number", nullable = false)
    private Long productId;

    @Column(name = "member_number", nullable = false)
    private Long memberId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "address", nullable = false)
    private String address;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public OrderEntity(
            final Long id,
            final Long productId,
            final Long memberId,
            final Integer quantity,
            final Integer price,
            final String address,
            final LocalDateTime createdAt,
            final LocalDateTime deletedAt
    ) {
        this.id = id;
        this.productId = productId;
        this.memberId = memberId;
        this.quantity = quantity;
        this.price = price;
        this.address = address;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public static OrderEntity create(final OrderIssueRequest orderIssueRequest, final int price) {
        return OrderEntity.builder()
                .productId(orderIssueRequest.productId())
                .memberId(orderIssueRequest.memberId())
                .quantity(orderIssueRequest.quantity())
                .price(price)
                .address(orderIssueRequest.address())
                .build();
    }
}
