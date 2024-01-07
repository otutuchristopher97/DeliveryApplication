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
    public Parcel addParcel(@RequestBody ParcelDTO parcelDTO, @AuthenticationPrincipal User user){
       User loggedInUser = userService.authenticatedUser(user);
       parcelDTO.setUser_id(loggedInUser.getId());
       return service.saveParcel(parcelDTO);
    }
    @GetMapping("/parcels")
    public List<Parcel> findAllParcels(){
       return service.getParcels();
    }
    @GetMapping("/parcel/{id}")
    public Parcel findParcelById(@PathVariable int id){
       return service.getParcelById(id);
    }
    @PutMapping("/parcel/update")
    public Parcel updateParcel(@RequestBody Parcel parcel){
       return service.updateParcel(parcel);
    }
    @DeleteMapping("/parcel/delete/{id}")
    public String deleteParcel(@PathVariable int id){
       return service.deleteParcel(id);
    }


}
