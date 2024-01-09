package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

public interface UserCommand {
    int ADD_USER = 1;

    int GET_ALL_USER = 3;

    int GET_CURRENT_USER = 4;
    int GET_USER_BY_ID = 5;

    int UPDATE_USER = 6;

    int DELETE_USER = 7;
    int GET_USER_BY_EMAIL = 8;
    int GET_USER_BY_ROLE_ID = 9;

    Object execute();
}