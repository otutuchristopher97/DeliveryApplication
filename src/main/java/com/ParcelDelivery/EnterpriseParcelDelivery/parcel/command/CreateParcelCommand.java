package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.ParcelFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateParcelCommand implements ParcelCommand{
    private final ParcelRepository parcelRepository;
    private final ParcelFactory parcelFactory;
    private final ParcelDTO parcelDTO;

    public Parcel execute(){
        Parcel parcel = parcelFactory.createEntity(parcelDTO);
        return parcelRepository.save(parcel);
    }
}
