package com.ParcelDelivery.EnterpriseParcelDelivery.exception;

import lombok.RequiredArgsConstructor;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
