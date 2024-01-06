package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

public interface DriverCommand {
    int CREATE_DRIVER = 1;
    int GET_DRIVER = 2;

    Object execute();
}
