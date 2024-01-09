package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetUserByRoleIdCommand implements UserCommand{
    private final UserRepository userRepository;
    private final Integer id;
    public List<User> execute(){
        return userRepository.findByRoleId(id);
    }
}
