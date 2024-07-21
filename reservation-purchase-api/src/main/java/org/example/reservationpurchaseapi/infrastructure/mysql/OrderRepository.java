package org.example.reservationpurchaseapi.infrastructure.mysql;

import org.example.reservationpurchaseapi.infrastructure.mysql.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
