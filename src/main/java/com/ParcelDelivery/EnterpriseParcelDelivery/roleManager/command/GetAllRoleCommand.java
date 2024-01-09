package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllRoleCommand implements RoleCommand {
    private final RoleRepository roleRepository;
    public List<Role> execute(){
        return roleRepository.findAll();
    }
}
