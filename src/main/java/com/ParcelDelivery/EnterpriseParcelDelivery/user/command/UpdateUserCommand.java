package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserCommand implements UserCommand{
    private final UserRepository userRepository;
    private final User user;
    public User execute(){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        return userRepository.save(existingUser);
    }
}
