package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllParcelByIdCommand implements ParcelCommand{
    private final ParcelRepository parcelRepository;
    private final Integer id;
    public Parcel execute(){
        Parcel parcel = parcelRepository.findById(id).orElse(null);
        if (parcel==null){
            throw new BadRequestException("Parcel not found with id: " + id);
        }
        return parcel;
    }
}
