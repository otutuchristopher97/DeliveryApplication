package com.ParcelDelivery.EnterpriseParcelDelivery.user;

import com.ParcelDelivery.EnterpriseParcelDelivery.exception.BadRequestException;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/user/add")
    public ResponseEntity<User> saveUser(@RequestBody @Valid  UserDTO userDTO){
        userDTO.setRole_id(1);
        return new ResponseEntity<>(service.saveUser(userDTO), HttpStatus.CREATED);

    }
    @PostMapping("/user/admin/add")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<User> createAdminAccount(@RequestBody @Valid  UserDTO userDTO){
        userDTO.setRole_id(3);
        return new ResponseEntity<>(service.saveUser(userDTO), HttpStatus.CREATED);

    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(value="role_id",required = false) Integer role_id){
        if(role_id !=null){
            return ResponseEntity.ok(service.getUsersByRoleId(role_id));
        }
        return ResponseEntity.ok(service.getUsers());

    }
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){

        return service.findUserById(id);
    }
    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user){

        return service.updateUser(user);
    }
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id){

        return service.deleteUser(id);
    }
    @PostMapping("/authenticate")
    public AuthResponseDTO generateToken(@RequestBody AuthDTO authDTO) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
            );
        }catch(Exception ex){
            throw new BadRequestException("Invalid email and password");
        }
        String token = jwtUtil.generateToken(authDTO.getEmail());
        User user = service.findUserByEmail(authDTO.getEmail());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setToken(token);
        response.setRole_id(user.getRole().getId());
        return response;
    }

    @GetMapping("/authorities")
    public List<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/authenticated")
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return service.authenticatedUser(user);
    }
}
