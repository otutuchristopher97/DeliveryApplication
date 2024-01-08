package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryAddressFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveDeliveryAddressCommand implements DeliveryAddressCommand {
    private final DeliveryAddressRepository repository;
    private final DeliveryAddressFactory deliveryAddressFactory;
    private final DeliveryAddressDTO deliveryAddressDTO;

    @Override
    public RecipientAddress execute()
    {
        RecipientAddress deliveryAddress = deliveryAddressFactory.createEntity(deliveryAddressDTO);
        return repository.save(deliveryAddress);
    }
}
