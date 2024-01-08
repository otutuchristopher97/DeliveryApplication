package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager;

import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository repository;
    public Role saveRole(Role role){
        return repository.save(role);
    }
    public List<Role> getRoles(){
        return repository.findAll();
    }
    public Role findRoleById(int id){
        Role role = repository.findById(id).orElse(null);
        if(role==null){
            throw new BadRequestException("Role not found with id"+id);
        }
        return role;
    }
    public Role updateRole(Role role){
        Role existingRole = repository.findById(role.getId()).orElse(null);
        existingRole.setName(role.getName());
        return repository.save(existingRole);
    }
    public String deleteRole(int id){
        repository.deleteById(id);
        return "Role deleted";
    }
}
