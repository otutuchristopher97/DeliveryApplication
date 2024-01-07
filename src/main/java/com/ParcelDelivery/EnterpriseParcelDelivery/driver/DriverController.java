package com.ParcelDelivery.EnterpriseParcelDelivery.driver;

import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DriverFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.ok(service
                .getAllDriver()
                .stream()
                .map(driverDtoFactory::createDriverDTO)
                .collect(Collectors.toCollection(ArrayList::new)));
    }
    @GetMapping("/driver/{id}")
    public DriverDTO getDriverById(@PathVariable(name = "id") @Min(value = 1, message = "Id must be greater than zero") int id){
        return driverDtoFactory.createDriverDTO(service.getDriverById(id));
    }
    @PutMapping("/driver/update/{id}")
    public DriverDTO updateDriver(@PathVariable(name = "id") @Min(value = 1, message = "Id must be greater than zero") int id,
                               @Valid @RequestBody @NotBlank(message = "Address cannot be blank") String address, @Valid @RequestBody @NotBlank(message = "Address cannot be blank") String phone_number){
        return driverDtoFactory.createDriverDTO(service.updateDriver(id, address, phone_number));
    }
    @DeleteMapping("/driver/delete/{id}")
    public Boolean deleteDriver(@PathVariable(name = "id")
                                   @Min(value = 1, message = "Id must be greater than zero") int id){
        return service.deleteDriver(id);
    }

}
