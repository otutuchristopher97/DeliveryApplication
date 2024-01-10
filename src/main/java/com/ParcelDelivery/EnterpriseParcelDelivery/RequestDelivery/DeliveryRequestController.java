package com.ParcelDelivery.EnterpriseParcelDelivery.RequestDelivery;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api")
public class DeliveryRequestController {
    private final DeliveryRequestService service;
    private final UserService userService;

    @PostMapping("/request/create")
    public DeliveryRequestResponseDTO saveDeliveryRequest(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO, @AuthenticationPrincipal User user){
        User loggedInUser = userService.authenticatedUser(user);
        if(loggedInUser==null){
            throw new BadRequestException("User not found");
        }
        deliveryRequestDTO.setUser_id(loggedInUser.getId());
        return service.saveDeliveryRequest(deliveryRequestDTO);
    }
    @PutMapping("/request/update")
    @PreAuthorize("hasAuthority('Admin')")
    public DeliveryRequestResponseDTO updateDeliveryRequest(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO){
        return service.updateRequest(deliveryRequestDTO);
    }
    @PutMapping("/request/assign-driver")
    @PreAuthorize("hasAuthority('Admin')")
    public DeliveryRequestResponseDTO assignRequestDriver(@RequestBody DeliveryRequestDTO deliveryRequestDTO){
        return service.assignDriver(deliveryRequestDTO);
    }
    @PutMapping("/request/update-status")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Driver')")
    public DeliveryRequestResponseDTO updateRequestStatus (@RequestBody DeliveryRequestDTO deliveryRequestDTO){
        return service.updateRequestStatus(deliveryRequestDTO);
    }
    @GetMapping("/requests")
    public List<DeliveryRequestResponseDTO> getAllDeliveryRequests(@RequestParam(value="driver_id",required = false) Integer driver_id){
        return service.getDeliveryRequests(driver_id);
    }
    @GetMapping("/request/{id}")
    public DeliveryRequestResponseDTO getDeliveryRequestById(@PathVariable int id){
        return service.getDeliveryRequestById(id);
    }
    @GetMapping("/request/filter")
    public List<DeliveryRequestResponseDTO> getDeliveryByFilter(@RequestParam(value="user_id",required = true) Integer user_id,@RequestParam(value="delivery_status_id",required = false) Integer delivery_status_id){
        return service.getDeliveryRequestsByUserIdAndDeliveryStatusId(user_id,delivery_status_id);
    }
}
