package com.ParcelDelivery.EnterpriseParcelDelivery.roleManager;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
