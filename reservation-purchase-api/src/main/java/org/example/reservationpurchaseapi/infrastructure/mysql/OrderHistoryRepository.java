package org.example.reservationpurchaseapi.infrastructure.mysql;

import org.example.reservationpurchaseapi.infrastructure.mysql.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
}
