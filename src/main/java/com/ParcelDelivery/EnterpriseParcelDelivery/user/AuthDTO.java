package com.ParcelDelivery.EnterpriseParcelDelivery.user;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;

}
