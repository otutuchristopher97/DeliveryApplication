package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.CreateDriverCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryAddressFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command.DeliveryAddressCommand.*;

@Component
@RequiredArgsConstructor
public class DeliveryAddressCommandFactory {
    private final DeliveryAddressRepository repository;
    private final DeliveryAddressFactory deliveryAddressFactory;
    public DeliveryAddressCommand create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case SAVE_DELIVERY_ADDRESS:
                return new SaveDeliveryAddressCommand(repository, deliveryAddressFactory, (DeliveryAddressDTO)params[0]);
            case GET_DELIVERY_ADDRESS:
                return new GetDeliveryAddressCommand(repository, (Integer)params[0]);
            case GET_All_DELIVERY_ADDRESS:
                return new GetAllDeliveryAddressCommand(repository);
            case UPDATE_DELIVERY_ADDRESS:
                return new UpdateDeliveryAddressCommand(repository, deliveryAddressFactory, (DeliveryAddressDTO)params[0]);
            case DELETE_DELIVERY_ADDRESS:
                return new DeleteDeliveryAddressCommand(repository, (Integer)params[0]);
            default:
                return null;
        }
    }
}
