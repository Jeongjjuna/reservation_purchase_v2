package org.example.reservationpurchaseorderconsumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reservationpurchaseapi.common.exception.GlobalException;
import org.example.reservationpurchaseapi.infrastructure.mysql.OrderHistoryRepository;
import org.example.reservationpurchaseapi.infrastructure.mysql.OrderRepository;
import org.example.reservationpurchaseapi.infrastructure.mysql.StockRepository;
import org.example.reservationpurchaseapi.infrastructure.mysql.entity.OrderEntity;
import org.example.reservationpurchaseapi.infrastructure.mysql.entity.OrderHistoryEntity;
import org.example.reservationpurchaseapi.infrastructure.mysql.entity.StockEntity;
import org.example.reservationpurchaseapi.infrastructure.redis.RedisRepository;
import org.example.reservationpurchaseapi.infrastructure.redis.dto.OrderIssueRequest;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderIssueListener {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final StockRepository stockRepository;
    private final RedisRepository redisRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Scheduled(fixedDelay = 1000L)
    public void issue() throws JsonProcessingException {
        log.info("listening...");
        while (existOrderIssue()) {
            final OrderIssueRequest target = getIssueTarget();
            log.info("발급 시작 target: " + target);

            // orders 저장
            // 임시 가격 지정
            int price = 1000;
            OrderEntity order = OrderEntity.create(target, price);
            orderRepository.save(order);

            // order_history 저장
            OrderHistoryEntity orderHistory = OrderHistoryEntity.create(order);
            orderHistoryRepository.save(orderHistory);

            // 상품 재고 감소
            StockEntity stock = stockRepository.findByProductId(target.productId())
                    .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "[ERROR] 상품 재고 찾을 수 없음."));
            stock.substract();
            stockRepository.save(stock);

            log.info("발급 완료 target: " + target);
            removeIssuedTarget();
        }
    }

    private boolean existOrderIssue() {
        String issueRequestKey = "issue.request";
        return redisRepository.lSize(issueRequestKey) > 0;
    }

    private OrderIssueRequest getIssueTarget() throws JsonProcessingException {
        String issueRequestKey = "issue.request";
        return objectMapper.readValue(redisRepository.lIndex(issueRequestKey, 0), OrderIssueRequest.class);
    }

    private void removeIssuedTarget() {
        String issueRequestKey = "issue.request";
        redisRepository.lPop(issueRequestKey);
    }
}
