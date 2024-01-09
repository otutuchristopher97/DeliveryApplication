package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.UserFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.command.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.user.command.UserCommand.*;

@Component
@RequiredArgsConstructor
public class UserCommandFactory {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserFactory userFactory;

    public UserCommand create(int commandCode, Object... params) {
        switch (commandCode) {
            case ADD_USER:
                return new AddUserCommand(userRepository, roleRepository, userFactory, (UserDTO) params[0]);
            case GET_ALL_USER:
                return new GetAllUserCommand(userRepository);
            case GET_USER_BY_ID:
                return new GetUserByIDCommand(userRepository, (Integer) params[0]);
            case GET_USER_BY_ROLE_ID:
                return new GetUserByRoleIdCommand(userRepository, (Integer) params[0]);
            case UPDATE_USER:
                return new UpdateUserCommand(userRepository, (User) params[0]);
            case DELETE_USER:
                return new DeleteUserCommand(userRepository, (Integer) params[0]);
            case GET_CURRENT_USER:
                return new GetCurrentUserCommand(userRepository);
            case GET_USER_BY_EMAIL:
                return new GetUserByEmailCommand(userRepository, (String) params[0]);
            default:
                return null;
        }
    }
}
