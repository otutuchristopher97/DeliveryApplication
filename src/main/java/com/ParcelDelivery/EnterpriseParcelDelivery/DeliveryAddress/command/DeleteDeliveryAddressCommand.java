package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteDeliveryAddressCommand implements DeliveryAddressCommand{
    private final DeliveryAddressRepository repository;
    private final int id;

    @Override
    public Boolean execute()
    {
        if (repository.existsById(id))
        {
            try
            {
                repository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }
}
