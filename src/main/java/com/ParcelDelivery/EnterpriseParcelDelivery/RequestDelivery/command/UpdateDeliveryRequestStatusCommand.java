package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryRequest;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryStatus;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery.DeliveryStatusRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateDeliveryRequestStatusCommand implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final DeliveryRequestDTO deliveryRequestDTO;
    public DeliveryRequestResponseDTO execute(){
        DeliveryRequest existingRequest  = deliveryRequestRepository.findById(deliveryRequestDTO.getId()).orElse(null);
        if(existingRequest==null){
            throw new BadRequestException("Request not found");
        }
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(deliveryRequestDTO.getDelivery_status_id()).orElse(null);
        if(deliveryStatus==null){
            throw new BadRequestException("Request not found");
        }
        existingRequest.setDeliveryStatus(deliveryStatus);
        deliveryRequestRepository.save(existingRequest);
        return deliveryRequestFactory.createEntityResponse(existingRequest);
    }
}
