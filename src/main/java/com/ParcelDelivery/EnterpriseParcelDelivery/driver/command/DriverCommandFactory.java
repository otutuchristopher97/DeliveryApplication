package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.UserFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.repository.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommand.CREATE_DRIVER;
import static com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.DriverCommand.GET_DRIVER;

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
            default:
                return null;
        }
    }
}