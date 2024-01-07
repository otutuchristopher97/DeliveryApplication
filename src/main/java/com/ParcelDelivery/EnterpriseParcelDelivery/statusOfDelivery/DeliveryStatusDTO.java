package com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class DeliveryStatusDTO {
    private int id;
    @NotNull
    private String status;
}
