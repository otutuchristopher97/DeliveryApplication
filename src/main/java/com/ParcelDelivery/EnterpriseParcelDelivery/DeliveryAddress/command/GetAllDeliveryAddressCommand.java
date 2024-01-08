package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllDeliveryAddressCommand implements DeliveryAddressCommand{
    private final DeliveryAddressRepository deliveryAddressRepository;
    @Override
    public List<RecipientAddress> execute()
    {
        return deliveryAddressRepository.findAll();
    }
}
