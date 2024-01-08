package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command.DeliveryAddressCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command.DeliveryAddressCommandFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommandFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryAddressFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeliveryAddressService {
    private final DeliveryAddressRepository repository;
    private final DeliveryAddressFactory deliveryAddressFactory;
    private final DeliveryAddressCommandFactory deliveryAddressCommandFactory;



    public RecipientAddress saveRecipientAddress(DeliveryAddressDTO deliveryAddressDTO){

        return (RecipientAddress) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.SAVE_DELIVERY_ADDRESS, deliveryAddressDTO).execute();
    }

    public List<RecipientAddress> getRecipientAddresses(){
        return (List<RecipientAddress>) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.GET_All_DELIVERY_ADDRESS)
                .execute();

    }
    public RecipientAddress getRecipientAddressById(int id){
        return (RecipientAddress) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.GET_DELIVERY_ADDRESS, id)
                .execute();
    }
    public List<RecipientAddress> findListOfRecipientAddressByUserId(int user_id){
        return (List<RecipientAddress>) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.GET_All_DELIVERY_ADDRESS_BY_ID)
                .execute();
    }
    public RecipientAddress updateRecipientAddress(DeliveryAddressDTO deliveryAddressDTO){
        return (RecipientAddress) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.UPDATE_DELIVERY_ADDRESS, deliveryAddressDTO).execute();
    }
    public Boolean deleteRecipientAddress(int id){
        return (Boolean) deliveryAddressCommandFactory
                .create(DeliveryAddressCommand.DELETE_DELIVERY_ADDRESS, id).execute();
    }


}
