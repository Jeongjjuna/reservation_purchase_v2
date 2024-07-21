package org.example.reservationpurchaseapi.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.reservationpurchaseapi.domain.OrderStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Entity
@Table(name = "order_history")
public class OrderHistoryEntity {

    @Id
    @Column(name = "orders_number", updatable = false)
    private Long orderId;

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

    @Enumerated(EnumType.STRING) // Enum 값을 문자열로 매핑
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public OrderHistoryEntity(
            final Long orderId,
            final Long productId,
            final Long memberId,
            final Integer quantity,
            final Integer price,
            final String address,
            final OrderStatus status,
            final LocalDateTime createdAt
    ) {
        this.orderId = orderId;
        this.productId = productId;
        this.memberId = memberId;
        this.quantity = quantity;
        this.price = price;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static OrderHistoryEntity create(final OrderEntity order) {
        return OrderHistoryEntity.builder()
                .orderId(order.getId())
                .productId(order.getProductId())
                .memberId(order.getMemberId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .address(order.getAddress())
                .status(OrderStatus.PROCESSING)
                .build();
    }
}
