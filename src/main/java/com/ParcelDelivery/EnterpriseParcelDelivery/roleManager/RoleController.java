package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoleController {
    private final RoleService service;
    @PostMapping("/role/add")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<>(service.createRole(role), HttpStatus.CREATED);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles(){
        return ResponseEntity.ok(service.getRoles());
    }
    @GetMapping("/role/{id}")
    public Role getRoleById(@PathVariable int id){
        return service.getRoleById(id);
    }
    @PutMapping("/role/update")
    public Role updateRole(@RequestBody Role role){
        return service.updateRole(role);
    }
    @DeleteMapping("role/delete")
    public Boolean deleteRole(@PathVariable int id){
        return service.deleteRole(id);
    }

}
