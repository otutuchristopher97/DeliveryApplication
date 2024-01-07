package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
public class DriverDTO {
    private int id;
    @NotNull
    private int user_id;
    @NotNull(message="Phone number is required")
    private String phone_number;
    @NotNull(message="Address is required")
    private String address;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String password;


}
