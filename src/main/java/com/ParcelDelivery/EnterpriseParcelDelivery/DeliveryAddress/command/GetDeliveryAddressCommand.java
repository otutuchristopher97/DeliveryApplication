package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDeliveryAddressCommand implements DeliveryAddressCommand {
    private final DeliveryAddressRepository repository;
    private final int id;

    @Override
    public RecipientAddress execute()
    {
        return repository.findById(id).orElse(null);
    }
}
