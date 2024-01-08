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
public class GetAllDeliveryRequestsCommand implements DeliveryRequestCommand{
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DeliveryRequestFactory deliveryRequestFactory;
    private final Integer id;

    public List<DeliveryRequestResponseDTO> execute()
    {
        return deliveryRequestRepository.findByDriverId(id).stream()
            .map(deliveryRequestFactory::createEntityResponse)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
