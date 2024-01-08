package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command.DeliveryRequestCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command.DeliveryRequestCommandFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery.DeliveryStatusRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryRequestService {
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final ParcelRepository parcelRepository;
    private final DeliveryRequestCommandFactory deliveryRequestCommandFactory;

    private final DeliveryRequestFactory deliveryRequestFactory;


    public DeliveryRequestResponseDTO saveDeliveryRequest(DeliveryRequestDTO deliveryRequestDTO){
        return (DeliveryRequestResponseDTO) deliveryRequestCommandFactory
                .create(DeliveryRequestCommand.SAVE_DELIVERY_REQUEST, deliveryRequestDTO).execute();
    }
    public DeliveryRequestResponseDTO updateRequestStatus(DeliveryRequestDTO deliveryRequestDTO){
        return (DeliveryRequestResponseDTO) deliveryRequestCommandFactory
                .create(DeliveryRequestCommand.UPDATE_DELIVERY_REQUEST_STATUS, deliveryRequestDTO).execute();

    }
    public DeliveryRequestResponseDTO assignDriver(DeliveryRequestDTO deliveryRequestDTO){
        return (DeliveryRequestResponseDTO) deliveryRequestCommandFactory
                .create(DeliveryRequestCommand.ASSIGN_REQUEST_DELIVERY_DRIVER, deliveryRequestDTO).execute();
    }
    public DeliveryRequestResponseDTO updateRequest(DeliveryRequestDTO deliveryRequestDTO){
        return (DeliveryRequestResponseDTO) deliveryRequestCommandFactory
                .create(DeliveryRequestCommand.UPDATE_DELIVERY_REQUEST, deliveryRequestDTO).execute();

    }

    public List<DeliveryRequestResponseDTO> getDeliveryRequests(Integer id){
        try{
            return (List<DeliveryRequestResponseDTO>) deliveryRequestCommandFactory
                    .create(DeliveryRequestCommand.GET_All_DELIVERY_REQUEST_BY_ID, id).execute();
        } catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }


    }
    public DeliveryRequestResponseDTO getDeliveryRequestById(int id){
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(id).orElse(null);
        if(deliveryRequest==null){
            throw new BadRequestException("Request not found");
        }
        return deliveryRequestFactory.createEntityResponse(deliveryRequest);
    }
    public List<DeliveryRequestResponseDTO> getDeliveryRequestsByUserIdAndDeliveryStatusId(Integer user_id, Integer delivery_status_id){
        List<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findByUserId(user_id);
        if(delivery_status_id !=null){
            deliveryRequests = deliveryRequestRepository.findByUserIdAndDeliveryStatusId(user_id,delivery_status_id);
        }
        List<DeliveryRequestResponseDTO> dtos = new ArrayList<>();
        for (DeliveryRequest deliveryRequest: deliveryRequests){
            DeliveryRequestResponseDTO dto = deliveryRequestFactory.createEntityResponse(deliveryRequest);
            dtos.add(dto);
        }
        return dtos;
    }

}
