package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.DeliveryRequestRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryRequestFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery.DeliveryStatusRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery.command.DeliveryRequestCommand.*;

@RequiredArgsConstructor
@Component
public class DeliveryRequestCommandFactory {
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final ParcelRepository parcelRepository;

    private final DeliveryRequestFactory deliveryRequestFactory;

    public DeliveryRequestCommand create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case SAVE_DELIVERY_REQUEST:
                return new SaveDeliveryRequestCommand(deliveryRequestRepository, driverRepository, userRepository, deliveryStatusRepository, parcelRepository, deliveryRequestFactory, (DeliveryRequestDTO)params[0]);
            case UPDATE_DELIVERY_REQUEST_STATUS:
                return new UpdateDeliveryRequestStatusCommand(deliveryRequestRepository, deliveryStatusRepository, deliveryRequestFactory, (DeliveryRequestDTO)params[0]);
            case ASSIGN_REQUEST_DELIVERY_DRIVER:
                return new AssignRequestDeliveryDriver(deliveryRequestRepository, driverRepository, deliveryRequestFactory, (DeliveryRequestDTO)params[0]);
            case GET_All_DELIVERY_REQUEST_BY_ID:
                return new GetAllDeliveryRequestsCommand(deliveryRequestRepository, deliveryRequestFactory, (Integer) params[0]);
            case GET_DELIVERY_REQUEST_BY_ID:
                return new GetDeliveryRequestByIdCommand(deliveryRequestRepository, deliveryRequestFactory, (Integer) params[0]);
            default:
                return null;
        }
    }
}
