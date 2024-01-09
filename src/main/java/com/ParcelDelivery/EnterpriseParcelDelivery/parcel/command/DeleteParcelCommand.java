package com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteParcelCommand implements ParcelCommand{
    private final ParcelRepository parcelRepository;
    private final Integer id;

    public Boolean execute(){
        if (parcelRepository.existsById(id))
        {
            try
            {
                parcelRepository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }
}
