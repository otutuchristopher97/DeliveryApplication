package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

public interface DriverCommand {
    int CREATE_DRIVER = 1;
    int GET_DRIVER = 2;

    int GET_All_DRIVER = 3;

    int UPDATE_DRIVER = 4;
    int DELETE_DRIVER = 5;

    Object execute();
}
