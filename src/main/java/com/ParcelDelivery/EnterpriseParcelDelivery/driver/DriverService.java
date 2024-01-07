package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverService {
    private final DriverCommandFactory driverCommandFactory;

    public Driver saveDriver(DriverDTO dto){

        return (Driver) driverCommandFactory
                        .create(DriverCommand.CREATE_DRIVER, dto).execute();
    }
    public List<Driver> getAllDriver() {
        return (List<Driver>) driverCommandFactory
                .create(DriverCommand.GET_All_DRIVER)
                .execute();
    }
    public Driver getDriverById(int id){

        return (Driver) driverCommandFactory
                        .create(DriverCommand.GET_DRIVER, id)
                        .execute();
    }
    public Boolean deleteDriver(int id){
        return (Boolean) driverCommandFactory
                .create(DriverCommand.DELETE_DRIVER, id)
                .execute();
    }
    public Driver updateDriver(int id, String address, String phone){
        return (Driver) driverCommandFactory
                .create(DriverCommand.UPDATE_DRIVER, id, address, phone)
                .execute();
    }
}
