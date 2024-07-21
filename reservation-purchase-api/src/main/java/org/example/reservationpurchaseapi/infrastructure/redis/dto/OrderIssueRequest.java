package org.example.reservationpurchaseapi.infrastructure.redis.dto;

public record OrderIssueRequest(Long productId, Long memberId, Integer quantity, String address) {
}
