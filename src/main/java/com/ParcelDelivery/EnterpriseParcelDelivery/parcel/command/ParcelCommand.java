package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

public interface ParcelCommand {
    int CREATE_PARCEL = 1;
    int GET_ALL_PARCEL = 2;

    int GET_PARCEL_BY_ID = 3;

    int UPDATE_PARCEL = 4;
    int DELETE_PARCEL = 5;

    Object execute();
}