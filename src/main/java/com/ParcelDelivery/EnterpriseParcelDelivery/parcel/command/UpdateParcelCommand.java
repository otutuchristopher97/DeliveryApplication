package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.ParcelFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateParcelCommand implements ParcelCommand{
    private final ParcelRepository parcelRepository;
    private final ParcelDTO parcelDTO;
    private final Integer id;
    public Parcel execute(){
        Parcel existingParcel = parcelRepository.findById(id).orElse(null);
        assert existingParcel != null;
        existingParcel.setName(parcelDTO.getName());
        existingParcel.setDescription(parcelDTO.getDescription());
        return parcelRepository.save(existingParcel);
    }
}
