package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllParcelCommand implements ParcelCommand{
    private final ParcelRepository parcelRepository;
    public List<Parcel> execute(){
        return parcelRepository.findAll();
    }
}
