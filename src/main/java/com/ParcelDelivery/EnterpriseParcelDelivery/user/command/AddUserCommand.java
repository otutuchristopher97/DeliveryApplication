package com.ParcelDelivery.EnterpriseParcelDelivery.user.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.ParcelFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.UserFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class AddUserCommand implements UserCommand{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserFactory userFactory;
    private final UserDTO userDTO;

    public User execute(){
        Role role = roleRepository.findById(userDTO.getRole_id()).orElse(null);
        if (role == null) {
            throw new BadRequestException("Role not found with id: " + userDTO.getRole_id());
        }
        User checkUser = userRepository.findByEmail(userDTO.getEmail());
        if(checkUser!=null){
            throw new BadRequestException("User already exist");
        }
        User user = userFactory.createEntity(userDTO,role);
        return userRepository.save(user);
    }
}