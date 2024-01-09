package com.ParcelDelivery.EnterpriseParcelDelivery.parcel;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command.DeliveryRequestCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.ParcelFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command.ParcelCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command.ParcelCommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ParcelService {

    private final ParcelRepository repository;
    private final ParcelCommandFactory parcelCommandFactory;

    public Parcel saveParcel(ParcelDTO parcelDTO){
        return (Parcel) parcelCommandFactory
                .create(ParcelCommand.CREATE_PARCEL, parcelDTO).execute();
    }

    public List<Parcel> getParcels(){
        return (List<Parcel>) parcelCommandFactory
                .create(ParcelCommand.GET_ALL_PARCEL).execute();
    }
    public Parcel getParcelById(int id){
        return (Parcel) parcelCommandFactory
                .create(ParcelCommand.GET_PARCEL_BY_ID, id).execute();
    }

    public Parcel updateParcel(int id, ParcelDTO parcelDTO){
        return (Parcel) parcelCommandFactory
                .create(ParcelCommand.UPDATE_PARCEL, parcelDTO, id).execute();
    }

    public Boolean deleteParcel(int id){
        return (Boolean) parcelCommandFactory
                .create(ParcelCommand.DELETE_PARCEL, id)
                .execute();
    }

}
