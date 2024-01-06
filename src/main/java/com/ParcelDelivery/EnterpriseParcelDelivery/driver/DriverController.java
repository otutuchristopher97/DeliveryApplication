package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Driver;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class DriverController {
    private final DriverService service;
    private final DriverFactory driverDtoFactory;

    @PostMapping("/driver/add")
    public DriverDTO saveDriver(@RequestBody @Valid DriverDTO driverDTO){
        return driverDtoFactory.createDriverDTO(service.saveDriver(driverDTO));
    }
    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDTO>> findAllDrivers(){
        return ResponseEntity.ok(service.getDrivers());
    }
    @GetMapping("/driver/{id}")
    public DriverDTO getDriverById(@PathVariable(name = "id") @Min(value = 1, message = "Id must be greater than zero") int id){
        return driverDtoFactory.createDriverDTO(service.getDriverById(id));
    }
    @PutMapping("/driver/update")
    public Driver updateDriver(@RequestBody Driver driver){
        return service.updateDriver(driver);
    }
    @DeleteMapping("/driver/delete")
    public String deleteDriver(@PathVariable int id){
        return service.deleteDriver(id);
    }

}
