package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command.RoleCommand.*;

@Component
@RequiredArgsConstructor
public class RoleCommandFactory {
    private final RoleRepository roleRepository;
    public RoleCommand create(int commandCode, Object... params) {
        switch (commandCode) {
            case CREATE_ROLE:
                return new CreateRoleCommand(roleRepository,(Role) params[0]);
            case GET_ALL_ROLE:
                return new GetAllRoleCommand(roleRepository);
            case GET_ROLE_BY_ID:
                return new GetRoleByIdCommand(roleRepository, (Integer) params[0]);
            case UPDATE_ROLE:
                return new UpdateRoleCommand(roleRepository, (Role) params[0]);
            case DELETE_ROLE:
                return new DeleteRoleCommand(roleRepository, (Integer) params[0]);
            default:
                return null;
        }
    }
}
