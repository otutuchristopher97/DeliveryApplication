package com.ParcelDelivery.EnterpriseParcelDelivery.UserController;

import com.ParcelDelivery.EnterpriseParcelDelivery.config.SecurityConfig;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.AuthResponseDTO;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.util.JwtUtil;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "com.faroukbakre.farbaksshop")
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private String baseUrl = "http://localhost";

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/");
    }

    @Test
    public void when_UserDoesNotExist_Expect_Success() throws Exception {
//        this.userRepository.deleteAll();

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("user/add")
                .toUriString();

        String json_userToCreate =
                "{\n  " +
                        "\"name\": \"chris2 Driver\",\n  " +
                        "\"email\": \"chris97+1@gmail.com\",\n  " +
                        "\"role_id\": 2,\n  " +
                        "\"password\": \"password12\"\n}";

        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("chris97+1@gmail.com"));

    }

    @Test
    public void when_UserWithEmailExists_Expect_ErrorMessage() throws Exception
    {
//        this.saveTestUser();
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("user/add")
                .toUriString();

        String json_userToCreate =
                "{\n  " +
                        "\"name\": \"chris Driver\",\n  " +
                        "\"email\": \"otutuchristopher97@gmail.com\",\n  " +
                        "\"role_id\": 1,\n  " +
                        "\"password\": \"password12\"\n}";

        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("User already exist"));
    }

    @Test
    public void when_UserDoesNotExistDuringLogin_Expect_ErrorMessage() throws Exception {
//        this.addressRepository.deleteAll();
//        this.userRepository.deleteAll();

        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("authenticate")
                .toUriString();

        String json_userToCreate = "{\n  \"email\": \"chris@gmail.com\",\n  \"password\": \"jsjfj\"\n}";

        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Invalid email and password"));

    }

    @Test
    public void when_UserPasswordIncorrectDuringLogin_Expect_ErrorMessage() throws Exception {
//        this.saveTestUser();
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("authenticate")
                .toUriString();

        String json_userToCreate =
                "{\n  " +
                        "\"email\": \"otutuchristopher97@gmail.com\",\n  " +
                        "\"password\": \"0001\"\n}";

        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Invalid email and password"));
    }

    @Test
    public void when_UserEmailAndPasswordValid_Expect_Success() throws Exception {
//        this.saveTestUser();
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("authenticate")
                .toUriString();
        log.info(url);
        String json_userToCreate =
                "{\n  " +
                        "\"email\": \"otutuchristopher97@gmail.com\",\n  " +
                        "\"password\": \"password1\"\n" +
                        "}";

        MvcResult mvcResult = mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.email").value("otutuchristopher97@gmail.com"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        AuthResponseDTO responseDto
                = new Gson().fromJson(responseBody, AuthResponseDTO.class);
        log.info("Token "+responseDto.getToken());
        Assertions.assertThat(responseDto.getEmail()).isEqualTo("otutuchristopher97@gmail.com");
    }

    @Test
    public void when_NoBearerTokenForProtectedEndpoints_Expect_ErrorMessage() throws Exception {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("user/authenticated")
                .toUriString();
        mockMvc
                .perform(get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    //    @Test
//    public void when_BearerTokenInvalid_Expect_ErrorMessage() throws Exception {
//        String url = UriComponentsBuilder.fromUriString(baseUrl)
//                .path("user/authenticated")
//                .toUriString();
//
//        mockMvc.perform(get(url)
//                        .header("AUTHORIZATION", "Bearer qwerftgd")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden());
//    }
//
    @Test
    public void when_BearerTokenExists_Expect_Success() throws Exception {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .path("user/authenticated")
                .toUriString();

        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvdHV0dWNocmlzdG9waGVyOTdAZ21haWwuY29tIiwiZXhwIjoxNzA0ODYyNTIyLCJpYXQiOjE3MDQ4NTg5MjJ9.efKAxoi1goh3lyhlu2xYpyj7LcmAobHDoKSCZ7MP6p0";
        mockMvc
                .perform(get(url)
                        .header("AUTHORIZATION", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Transactional
    public void saveTestUser() {
//        this.userRepository.deleteAll();

        Role role = this.roleRepository.findById(1).orElse(null);

        log.info("Role = "+ role.getName());
        User newUser = new User();
        newUser.setName("Chris otutu");
        newUser.setEmail("ik@gmail.com");
        String hashedPassword = passwordEncoder.encode("password1");
        newUser.setPassword(hashedPassword);
        newUser.setRole(role);
        try{
            this.userRepository.save(newUser);
        }catch (Exception e){
            log.info("Message = "+e.getMessage());
        }

    }
}
