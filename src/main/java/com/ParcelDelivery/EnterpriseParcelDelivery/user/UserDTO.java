package com.ParcelDelivery.EnterpriseParcelDelivery.user;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull(message = "Full name is required")
    private String name;
    @Email
    @NotNull(message="Email is required")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private int role_id;
}
