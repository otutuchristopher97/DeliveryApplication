package com.ParcelDelivery.EnterpriseParcelDelivery.parcel;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Parcel;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ParcelController {
    private final ParcelService service;
    private final UserService userService;

   @PostMapping("/parcel/add")
    public Parcel createParcel(@RequestBody ParcelDTO parcelDTO, @AuthenticationPrincipal User user){
       User loggedInUser = userService.authenticatedUser(user);
       parcelDTO.setUser_id(loggedInUser.getId());
       return service.saveParcel(parcelDTO);
    }
    @GetMapping("/parcels")
    public List<Parcel> getAllParcel(){
       return service.getParcels();
    }
    @GetMapping("/parcel/{id}")
    public Parcel getParcelById(@PathVariable int id){
       return service.getParcelById(id);
    }
    @PutMapping("/parcel/update/{id}")
    public Parcel updateParcel(@PathVariable int id, @RequestBody ParcelDTO parcelDTO){
       return service.updateParcel(id, parcelDTO);
    }
    @DeleteMapping("/parcel/delete/{id}")
    public Boolean deleteParcel(@PathVariable int id){
       return service.deleteParcel(id);
    }


}
