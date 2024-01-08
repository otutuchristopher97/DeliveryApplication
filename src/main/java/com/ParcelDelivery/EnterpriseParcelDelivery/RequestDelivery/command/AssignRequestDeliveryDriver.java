package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryRequest;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignRequestDeliveryDriver implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DriverRepository driverRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final DeliveryRequestDTO deliveryRequestDTO;

    public DeliveryRequestResponseDTO execute()
    {
        Driver driver = driverRepository.findById(deliveryRequestDTO.getDriver_id()).orElse(null);
        if(driver==null){
            throw new BadRequestException("Driver not found");
        }
        DeliveryRequest existingRequest  = deliveryRequestRepository.findById(deliveryRequestDTO.getId()).orElse(null);
        if(existingRequest==null){
            throw new BadRequestException("Request not found");
        }
        existingRequest.setDriver(driver);
        deliveryRequestRepository.save(existingRequest);
        return deliveryRequestFactory.createEntityResponse(existingRequest);
    }
}
