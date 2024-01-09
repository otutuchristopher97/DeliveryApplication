package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.factory.ParcelFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command.ParcelCommand.*;

@RequiredArgsConstructor
@Component
public class ParcelCommandFactory {
    private final ParcelRepository parcelRepository;
    private final ParcelFactory parcelFactory;
    public ParcelCommand create(int commandCode, Object... params) {
        switch (commandCode) {
            case CREATE_PARCEL:
                return new CreateParcelCommand(parcelRepository, parcelFactory,(ParcelDTO)params[0]);
            case GET_ALL_PARCEL:
                return new GetAllParcelCommand(parcelRepository);
            case GET_PARCEL_BY_ID:
                return new GetAllParcelByIdCommand(parcelRepository, (Integer) params[0]);
            case UPDATE_PARCEL:
                return new UpdateParcelCommand(parcelRepository, (ParcelDTO)params[0], (Integer) params[1]);
            case DELETE_PARCEL:
                return new DeleteParcelCommand(parcelRepository, (Integer) params[0]);
            default:
                return null;
        }
    }
}