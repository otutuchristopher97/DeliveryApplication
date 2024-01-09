package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserCommand implements UserCommand{
    private final UserRepository userRepository;
    private final Integer id;

    public Boolean execute(){
        if (userRepository.existsById(id))
        {
            try
            {
                userRepository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }
}