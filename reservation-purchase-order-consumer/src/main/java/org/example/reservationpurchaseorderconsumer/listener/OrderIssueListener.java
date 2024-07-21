package org.example.reservationpurchaseorderconsumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reservationpurchaseapi.infrastructure.redis.RedisRepository;
import org.example.reservationpurchaseapi.infrastructure.redis.dto.OrderIssueRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderIssueListener {

    private final RedisRepository redisRepository;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 1000L)
    public void issue() throws JsonProcessingException {
        log.info("listening...");
        while (existOrderIssue()) {
            final OrderIssueRequest target = getIssueTarget();
            log.info("발급 시작 target: " + target);

            // orders 저장

            // order_history 저장

            // 상품 재고 감소

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
