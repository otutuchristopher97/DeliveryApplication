package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllDeliveryAddressByIDCommand implements DeliveryAddressCommand{
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final int id;

    @Override
    public List<RecipientAddress> execute()
    {
        return deliveryAddressRepository.findRecipientAddressByUserId(id);
    }
}
