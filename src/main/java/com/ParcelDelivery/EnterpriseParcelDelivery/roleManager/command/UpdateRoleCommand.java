package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateRoleCommand implements RoleCommand{
    private final RoleRepository roleRepository;
    private final Role role;
    public Role execute(){
        Role existingRole = roleRepository.findById(role.getId()).orElse(null);
        existingRole.setName(role.getName());
        return roleRepository.save(existingRole);
    }
}
