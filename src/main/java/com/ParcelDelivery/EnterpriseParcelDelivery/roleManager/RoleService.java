package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command.RoleCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command.RoleCommandFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.command.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository repository;
    private final RoleCommandFactory roleCommandFactory;
    public Role createRole(Role role){
        return (Role) roleCommandFactory
                .create(RoleCommand.CREATE_ROLE, role).execute();
    }
    public List<Role> getRoles(){
        return (List<Role>) roleCommandFactory
                .create(RoleCommand.GET_ALL_ROLE).execute();
    }
    public Role getRoleById(int id){
        return (Role) roleCommandFactory
                .create(RoleCommand.GET_ROLE_BY_ID, id).execute();
    }
    public Role updateRole(Role role){
        return (Role) roleCommandFactory
                .create(RoleCommand.GET_ROLE_BY_ID, role).execute();
    }
    public Boolean deleteRole(int id){
        return (Boolean) roleCommandFactory
                .create(RoleCommand.DELETE_ROLE, id).execute();
    }
}
