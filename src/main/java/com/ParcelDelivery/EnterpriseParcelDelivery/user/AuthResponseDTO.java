package com.ParcelDelivery.EnterpriseParcelDelivery.user;
import lombok.Data;


@Data
public class AuthResponseDTO {
    private String token;
    private String email;
    private int id;
    private String name;
    private int role_id;
}
