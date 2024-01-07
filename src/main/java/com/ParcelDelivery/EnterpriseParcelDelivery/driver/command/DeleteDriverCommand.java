package com.ParcelDelivery.EnterpriseParcelDelivery.driver.command;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.DriverRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteDriverCommand implements DriverCommand{
    private final DriverRepository driverRepository;
    private final int id;

    @Override
    public Boolean execute()
    {
        if (driverRepository.existsById(id))
        {
            try
            {
                driverRepository.deleteById(id);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }

}
