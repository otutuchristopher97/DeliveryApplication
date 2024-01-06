package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import com.ParcelDelivery.EnterpriseParcelDelivery.driver.command.*;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverService {
    private final DriverRepository driverRepository;

    private final DriverFactory driverDTOFactory;

    public Driver saveDriver(DriverDTO dto){

        return
                (Driver) DriverCommandFactory
                        .create(DriverCommand.CREATE_DRIVER, dto).execute();
    }
    public List<DriverDTO> getDrivers(){
        List<Driver> drivers = driverRepository.findAll();

        List<DriverDTO> dtos = new ArrayList<>();
        for (Driver driver: drivers){
            DriverDTO dto = driverDTOFactory.createDriverDTO(driver);
            dtos.add(dto);
        }
        return dtos;

    }
    public Driver getDriverById(int id){

        return (Driver) DriverCommandFactory
                        .create(DriverCommand.CREATE_DRIVER, id)
                        .execute();
    }
    public String deleteDriver(int id){
        driverRepository.deleteById(id);
        return "Driver deleted";
    }
    public Driver updateDriver(Driver driver){
        Driver existingDriver = driverRepository.findById(driver.getId()).get();
        existingDriver.setAddress(driver.getAddress());
        existingDriver.setPhone_number(driver.getPhone_number());
        return driverRepository.save(existingDriver);
    }
}
