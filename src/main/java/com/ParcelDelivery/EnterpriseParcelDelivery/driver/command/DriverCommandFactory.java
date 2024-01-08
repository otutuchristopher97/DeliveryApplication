package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.UserFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommand.*;

@Component
@RequiredArgsConstructor
public class DriverCommandFactory
{
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final RoleRepository roleRepository;
    private final DriverFactory driverDTOFactory;

    public DriverCommand create(int commandCode, Object... params)
    {
        switch (commandCode)
        {
            case CREATE_DRIVER:
                return new CreateDriverCommand(driverRepository, userRepository, userFactory, roleRepository, driverDTOFactory, (DriverDTO)params[0]);
            case GET_DRIVER:
                return new GetDriverCommand(driverRepository, (Integer)params[0]);
            case GET_All_DRIVER:
                return new GetAllDriveCommand(driverRepository);
            case DELETE_DRIVER:
                return new DeleteDriverCommand(driverRepository, (Integer)params[0]);
            case UPDATE_DRIVER:
                return new UpdateDriverCommand(driverRepository, (Integer)params[0], (String)params[1], (String)params[2]);
            default:
                return null;
        }
    }
}