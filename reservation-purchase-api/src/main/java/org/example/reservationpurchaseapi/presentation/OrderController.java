package org.example.reservationpurchaseapi.presentation;

import lombok.AllArgsConstructor;
import org.example.reservationpurchaseapi.application.OrderService;
import org.example.reservationpurchaseapi.common.response.Response;
import org.example.reservationpurchaseapi.domain.OrderCreate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 생성
     */
    @PostMapping
    public Response<Long> create(@RequestBody final OrderCreate orderCreate) {
        orderService.create(orderCreate);
        return Response.success(null);
    }
}
