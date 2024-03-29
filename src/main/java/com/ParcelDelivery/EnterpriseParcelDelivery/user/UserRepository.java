package com.ParcelDelivery.EnterpriseParcelDelivery.user;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRoleId(int roleId);

    User findByEmail(String email);

}
