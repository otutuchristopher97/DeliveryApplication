package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteRoleCommand implements RoleCommand{
    private final RoleRepository roleRepository;
    private final Integer id;

    public Boolean execute(){
        if (roleRepository.existsById(id))
        {
            try
            {
                roleRepository.deleteById(id);
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