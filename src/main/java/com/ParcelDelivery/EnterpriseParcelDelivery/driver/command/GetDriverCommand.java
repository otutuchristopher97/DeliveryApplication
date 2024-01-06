package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDriverCommand implements DriverCommand {
    private final DriverRepository driverRepository;
    private final int id;

    @Override
    public Driver execute()
    {
        return driverRepository.findById(id).orElse(null);
    }
}
