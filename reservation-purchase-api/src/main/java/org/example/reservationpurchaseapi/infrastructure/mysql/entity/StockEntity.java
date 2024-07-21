package org.example.reservationpurchaseapi.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.reservationpurchaseapi.common.exception.GlobalException;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Entity
@Table(name = "product_stock")
public class StockEntity {

    @Id
    @Column(name = "product_number", updatable = false)
    private Long productId;

    @Column(name = "stock_count", nullable = false)
    private Integer stockCount;

    public void substract() {
        if (this.stockCount == 0) {
            throw new GlobalException(HttpStatus.CONFLICT, "[ERROR] 수량 감소 에러 발생");
        }
        this.stockCount -= 1;
    }
}