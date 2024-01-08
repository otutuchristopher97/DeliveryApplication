package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryRequest;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetDeliveryByFilterCommand implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final Integer userId;
    private final Integer deliveryStatusId;

    public List<DeliveryRequestResponseDTO> execute()
    {
        List<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findByUserIdAndDeliveryStatusId(userId,deliveryStatusId);
        if(deliveryRequests.isEmpty()){
            return new ArrayList<>();
        }
        return deliveryRequests.stream()
                .map(deliveryRequestFactory::createEntityResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
