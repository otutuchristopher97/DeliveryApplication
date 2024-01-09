package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRoleCommand implements RoleCommand {
    private final RoleRepository roleRepository;
    private final Role role;
    public Role execute(){
        return roleRepository.save(role);
    }
}
