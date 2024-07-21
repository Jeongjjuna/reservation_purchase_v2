package org.example.reservationpurchaseapi.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PROCESSING("product"),
    COMPLETED("reservationProduct"),
    CANCELED("canceled");

    private final String status;

    OrderStatus(final String status) {
        this.status = status;
    }
}
