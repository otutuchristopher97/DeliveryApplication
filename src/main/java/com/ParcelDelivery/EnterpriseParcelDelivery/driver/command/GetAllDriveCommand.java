package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllDriveCommand implements DriverCommand {
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> execute()
    {
        return driverRepository.findAll();
    }

}
