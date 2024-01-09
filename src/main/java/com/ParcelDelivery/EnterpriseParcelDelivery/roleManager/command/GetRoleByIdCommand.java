package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetRoleByIdCommand implements RoleCommand{
    private final RoleRepository roleRepository;
    private final Integer id;
    public Role execute(){
        Role role = roleRepository.findById(id).orElse(null);
        if(role==null){
            throw new BadRequestException("Role not found with id"+id);
        }
        return role;
    }
}
