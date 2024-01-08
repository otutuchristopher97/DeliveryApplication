package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

public interface DeliveryRequestCommand {
    int SAVE_DELIVERY_REQUEST = 1;
    int ASSIGN_REQUEST_DELIVERY_DRIVER = 2;

    int UPDATE_DELIVERY_REQUEST = 3;

    int GET_All_DELIVERY_REQUEST_BY_ID = 4;
    int UPDATE_DELIVERY_REQUEST_STATUS = 5;
    int GET_DELIVERY_REQUEST_BY_ID = 6;

    Object execute();
}


