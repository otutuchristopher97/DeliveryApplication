package com.ParcelDelivery.EnterpriseParcelDelivery.parcel;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
@Data
public class ParcelDTO {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private  String  description;

    private int user_id;
}
