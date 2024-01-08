package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryRequest;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetDeliveryRequestByIdCommand implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final Integer id;

    public DeliveryRequestResponseDTO execute()
    {
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(id).orElse(null);
        if(deliveryRequest==null){
            throw new BadRequestException("Request not found");
        }
        return deliveryRequestFactory.createEntityResponse(deliveryRequest);
    }
}
