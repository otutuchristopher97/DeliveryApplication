package com.ParcelDelivery.EnterpriseParcelDelivery.controller;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.service.RoleService;
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
        return new ResponseEntity<>(service.saveRole(role), HttpStatus.CREATED);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles(){
        return ResponseEntity.ok(service.getRoles());
    }
    @GetMapping("/role/{id}")
    public Role getRoleById(@PathVariable int id){
        return service.findRoleById(id);
    }
    @PutMapping("/role/update")
    public Role updateRole(@RequestBody Role role){
        return service.updateRole(role);
    }
    @DeleteMapping("role/delete")
    public String deleteRole(@PathVariable int id){
        return service.deleteRole(id);
    }

}
