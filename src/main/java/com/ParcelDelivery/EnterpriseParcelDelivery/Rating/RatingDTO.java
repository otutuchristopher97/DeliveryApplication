package com.ParcelDelivery.EnterpriseParcelDelivery.Rating;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class RatingDTO {
    private int id;
    @NotNull
    private int delivery_request_id;
    @NotNull
    private int sender_rating;
}
