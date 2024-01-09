package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByIDCommand implements UserCommand{
    private final UserRepository userRepository;
    private final Integer id;
    public User execute(){
        User user = userRepository.findById(id).orElse(null);
        if (user==null){
            throw new BadRequestException("Parcel not found with id: " + id);
        }
        return user;
    }
}
