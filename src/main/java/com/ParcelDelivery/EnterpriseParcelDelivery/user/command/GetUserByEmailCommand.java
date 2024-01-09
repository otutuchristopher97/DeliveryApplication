package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByEmailCommand implements UserCommand{
    private final UserRepository userRepository;
    private final String email;
    public User execute(){
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new BadRequestException("Parcel not found with email: " + email);
        }
        return user;
    }
}
