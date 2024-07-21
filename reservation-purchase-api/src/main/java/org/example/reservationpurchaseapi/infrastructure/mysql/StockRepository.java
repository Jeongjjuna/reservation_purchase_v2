package org.example.reservationpurchaseapi.infrastructure.mysql;

import org.example.reservationpurchaseapi.infrastructure.mysql.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProductId(Long productId);
}