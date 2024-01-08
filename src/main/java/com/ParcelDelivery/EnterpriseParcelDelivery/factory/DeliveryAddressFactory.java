package com.ParcelDelivery.EnterpriseParcelDelivery.factory;

import com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress.DeliveryAddressDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.RecipientAddress;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressFactory {

    private final UserRepository userRepository;
    public RecipientAddress createEntity(DeliveryAddressDTO dto){
        User user = userRepository.findById(dto.getUser_id()).orElse(null);
        RecipientAddress recipientAddress = new RecipientAddress();
        recipientAddress.setAddress(dto.getAddress());
        recipientAddress.setUser(user);
        recipientAddress.setRecipient_phone_number(dto.getRecipient_phone_number());
        recipientAddress.setRecipient_email(dto.getRecipient_email());
        return recipientAddress;
    }

    public RecipientAddress updateEntity(RecipientAddress deliveryAddress, DeliveryAddressDTO dto){
        deliveryAddress.setAddress(dto.getAddress());
        deliveryAddress.setRecipient_email(dto.getRecipient_email());
        deliveryAddress.setRecipient_phone_number(dto.getRecipient_phone_number());
        return deliveryAddress;
    }
    public DeliveryAddressDTO responseEntity(RecipientAddress recipientAddress){
        DeliveryAddressDTO dto = new DeliveryAddressDTO();
        dto.setUser_id(recipientAddress.getUser().getId());
        dto.setId(recipientAddress.getId());
        dto.setAddress(recipientAddress.getAddress());
        dto.setRecipient_email(recipientAddress.getRecipient_email());
        dto.setRecipient_phone_number(recipientAddress.getRecipient_phone_number());
        return dto;
    }
}
