package com.ParcelDelivery.EnterpriseParcelDelivery.DeliveryAddress;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class RecipientAddressController {
    private final RecipientAddressService service;
    private final UserService userService;
    @PostMapping("/recipient-address/save")
    public RecipientAddressDTO saveRecipientAddress(@RequestBody @Valid RecipientAddressDTO recipientAddressDTO,@AuthenticationPrincipal User user){
        User loggedInUser = userService.authenticatedUser(user);
        recipientAddressDTO.setUser_id(loggedInUser.getId());
        return service.saveRecipientAddress(recipientAddressDTO);

    }

    @PutMapping("/recipient-address/update")
    public RecipientAddressDTO updateRecipientAddress(@RequestBody @Valid RecipientAddressDTO recipientAddressDTO){
        return service.updateRecipientAddress(recipientAddressDTO);
    }
    @GetMapping("/recipient-address")
    public ResponseEntity<List<RecipientAddressDTO>> findAllAddress(@RequestParam(value="user_id",required = false) Integer user_id){
        List<RecipientAddressDTO> dtos = service.getRecipientAddresses();
        if(user_id !=null){
          dtos = service.findRecipientAddressByUserId(user_id);
        }
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/recipient-address/{id}")
    public RecipientAddressDTO findRecipientAddressById(@PathVariable int id){
        return service.getRecipientAddressById(id);

    }
    @DeleteMapping("/recipient-address/delete/{id}")
    public String deleteRecipientAddress(@PathVariable int id){
        return service.deleteRecipientAddress(id);
    }
}
