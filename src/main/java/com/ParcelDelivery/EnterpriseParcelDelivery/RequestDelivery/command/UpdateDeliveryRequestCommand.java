package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery.DeliveryStatusRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDeliveryRequestCommand implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final ParcelRepository parcelRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final DeliveryRequestDTO deliveryRequestDTO;

    @Override
    public DeliveryRequestResponseDTO execute()
    {
        Driver driver = driverRepository.findById(deliveryRequestDTO.getDriver_id()).orElse(null);
        if(driver==null){
            throw new BadRequestException("Driver not found");
        }
        User user  = userRepository.findById(deliveryRequestDTO.getUser_id()).orElse(null);
        if(user==null){
            throw new BadRequestException("User not found");
        }
        Parcel parcel = parcelRepository.findById(deliveryRequestDTO.getParcel_id()).orElse(null);
        if(parcel==null){
            throw new BadRequestException("Parcel not found");
        }
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(1).orElse(null);
        if(deliveryStatus==null){
            throw new BadRequestException("Delivery Status not found");
        }
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(deliveryRequestDTO.getId()).orElse(null);
        if(deliveryRequest==null){
            throw new BadRequestException("Delivery Request not found");
        }
        DeliveryRequest updateRequest = deliveryRequestFactory.updateEntity(deliveryRequest,deliveryRequestDTO,driver,user,parcel,deliveryStatus);
        deliveryRequestRepository.save(updateRequest);
        return deliveryRequestFactory.createEntityResponse(deliveryRequest);
    }
}
