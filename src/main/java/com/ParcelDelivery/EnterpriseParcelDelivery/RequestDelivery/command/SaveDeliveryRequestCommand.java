package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery.DeliveryStatusRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveDeliveryRequestCommand implements DeliveryRequestCommand{
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
        User user  = userRepository.findById(deliveryRequestDTO.getUser_id()).orElse(null);
        Parcel parcel = parcelRepository.findById(deliveryRequestDTO.getParcel_id()).orElse(null);
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(1).orElse(null);

        DeliveryRequest newDeliveryRequest = deliveryRequestFactory.createEntity(deliveryRequestDTO,driver,user,deliveryStatus,parcel);
        DeliveryRequest deliveryRequest = deliveryRequestRepository.save(newDeliveryRequest);
        return deliveryRequestFactory.createEntityResponse(deliveryRequest);
    }
}
