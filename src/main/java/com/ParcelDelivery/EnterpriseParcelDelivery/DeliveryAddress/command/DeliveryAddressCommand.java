package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.command;

public interface DeliveryAddressCommand {
    int SAVE_DELIVERY_ADDRESS = 1;
    int GET_DELIVERY_ADDRESS = 2;

    int GET_All_DELIVERY_ADDRESS = 3;

    int GET_All_DELIVERY_ADDRESS_BY_ID = 4;
    int UPDATE_DELIVERY_ADDRESS = 5;
    int DELETE_DELIVERY_ADDRESS = 6;

    Object execute();
}

