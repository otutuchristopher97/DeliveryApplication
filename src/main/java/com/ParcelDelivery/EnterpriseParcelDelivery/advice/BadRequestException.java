package com.ParcelDelivery.EnterpriseParcelDelivery.advice;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
