package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateDriverCommand implements DriverCommand{
    private final DriverRepository driverRepository;
    private final int driverId;
    private final String address;
    private final String phone;
    @Override
    public Driver execute()
    {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);

        if (!optionalDriver.isPresent())
        {
            return null;
        }

        Driver driver = optionalDriver.get();
        driver.setAddress(address);
        driver.setPhone_number(phone);
        return driverRepository.save(driver);
    }

}
