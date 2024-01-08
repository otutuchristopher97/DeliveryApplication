package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.UserFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateDriverCommand implements DriverCommand {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final RoleRepository roleRepository;
    private final DriverFactory driverDTOFactory;
    private final DriverDTO driverDTO;

    @Override
    public Driver execute()
    {
        Role role = roleRepository.findById(2).orElse(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(driverDTO.getEmail());
        userDTO.setPassword(driverDTO.getPassword());
        userDTO.setName(driverDTO.getName());
        userDTO.setRole_id(2);

        User newUser = userFactory.createEntity(userDTO,role);
        if (emailAlreadyInUse(newUser.getEmail()))
        {
            return null;
        }

        User user = userRepository.save(newUser);
        Driver driver = driverDTOFactory.createEntity(driverDTO,user);

        return driverRepository.save(driver);
    }

    private boolean emailAlreadyInUse(String email)
    {
        return userRepository.findByEmail(email) != null;
    }

}
