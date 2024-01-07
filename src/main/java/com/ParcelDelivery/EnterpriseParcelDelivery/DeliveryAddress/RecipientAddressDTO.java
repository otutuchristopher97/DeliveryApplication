package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class RecipientAddressDTO {
    private int id;

    private int user_id;
    @NotNull
    private String address;
    @NotNull
    private String recipient_phone_number;
    @NotNull
    private String recipient_email;

}
