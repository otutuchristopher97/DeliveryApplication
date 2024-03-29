package com.ParcelDelivery.EnterpriseParcelDelivery.statusOfDelivery;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DeliveryStatusController {
    private final DeliveryStatusService service;

    @PostMapping("/status/add")
    public ResponseEntity<DeliveryStatus> saveStatus(@RequestBody @Valid DeliveryStatus deliveryStatus){
        return new ResponseEntity<>(service.saveStatus(deliveryStatus), HttpStatus.CREATED);
    }
    @GetMapping("/statuses")
    public ResponseEntity<List> findAllStatus(){
        return ResponseEntity.ok(service.getStatuses());
    }
    @GetMapping("/status/{id}")
    public DeliveryStatus findStatusById(@PathVariable int id){
        return service.getStatusById(id);
    }
    @PutMapping("/status/update")
    public DeliveryStatus updateStatus(@RequestBody DeliveryStatus deliveryStatus){
        return service.updateStatus(deliveryStatus);
    }
    @DeleteMapping("/status/delete/{id}")
    public String deleteStatus(@PathVariable int id){
        return service.deleteStatus(id);
    }
}
