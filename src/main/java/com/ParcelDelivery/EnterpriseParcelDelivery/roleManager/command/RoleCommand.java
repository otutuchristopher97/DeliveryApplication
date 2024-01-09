package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

public interface RoleCommand {
    int CREATE_ROLE = 1;

    int GET_ALL_ROLE = 2;

    int GET_ROLE_BY_ID = 3;

    int UPDATE_ROLE = 4;

    int DELETE_ROLE = 5;
    Object execute();
}