package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryAddressFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDeliveryAddressCommand implements DeliveryAddressCommand{
    private final DeliveryAddressRepository repository;
    private final DeliveryAddressFactory deliveryAddressFactory;
    private final DeliveryAddressDTO deliveryAddressDTO;

    @Override
    public RecipientAddress execute()
    {
        RecipientAddress existingAddress = repository.findById(deliveryAddressDTO.getId()).orElse(null);
        if(existingAddress!=null){
            RecipientAddress recipientAddress = deliveryAddressFactory.updateEntity(existingAddress,deliveryAddressDTO);
            return repository.save(recipientAddress);
        }
        return null;
    }
}
