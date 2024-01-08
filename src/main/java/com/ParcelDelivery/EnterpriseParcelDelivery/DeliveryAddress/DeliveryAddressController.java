package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.factory.DeliveryAddressFactory;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class DeliveryAddressController {
    private final DeliveryAddressService service;
    private final UserService userService;
    private final DeliveryAddressFactory deliveryAddressFactory;
    @PostMapping("/delivery-address/save")
    public DeliveryAddressDTO saveDeliveryAddress(@RequestBody @Valid DeliveryAddressDTO deliveryAddressDTO, @AuthenticationPrincipal User user){
        User loggedInUser = userService.authenticatedUser(user);
        deliveryAddressDTO.setUser_id(loggedInUser.getId());
        return deliveryAddressFactory.responseEntity(service.saveRecipientAddress(deliveryAddressDTO));

    }

    @PutMapping("/delivery-address/update")
    public DeliveryAddressDTO updateDeliveryAddress(@RequestBody @Valid DeliveryAddressDTO deliveryAddressDTO){
        return deliveryAddressFactory.responseEntity(service.updateRecipientAddress(deliveryAddressDTO));
    }
    @GetMapping("/delivery-address")
    public ResponseEntity<List<DeliveryAddressDTO>> getAllDeliveryAddress(@RequestParam(value="user_id",required = false) Integer user_id){
        List<DeliveryAddressDTO> dtos = service
                .getRecipientAddresses()
                .stream()
                .map(deliveryAddressFactory::responseEntity)
                .collect(Collectors.toCollection(ArrayList::new));
        if(user_id !=null){
          dtos = service.findListOfRecipientAddressByUserId(user_id).stream()
                  .map(deliveryAddressFactory::responseEntity)
                  .collect(Collectors.toCollection(ArrayList::new));
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/delivery-address/{id}")
    public DeliveryAddressDTO getDeliveryAddressById(@PathVariable int id){
        return deliveryAddressFactory.responseEntity(service.getRecipientAddressById(id));
    }
    @DeleteMapping("/delivery-address/delete/{id}")
    public Boolean deleteDeliveryAddress(@PathVariable int id){
        return service.deleteRecipientAddress(id);
    }
}
