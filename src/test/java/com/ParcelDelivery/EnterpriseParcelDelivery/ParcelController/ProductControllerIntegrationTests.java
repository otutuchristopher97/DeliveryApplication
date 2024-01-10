package com.ParcelDelivery.EnterpriseParcelDelivery.ParcelController;

import com.ParcelDelivery.EnterpriseParcelDelivery.config.SecurityConfig;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.Role;
import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.parcel.ParcelRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.roleManager.RoleRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.UserRepository;
import com.ParcelDelivery.EnterpriseParcelDelivery.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan()
@AutoConfigureMockMvc
public class ProductControllerIntegrationTests {
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

    @Autowired
    ParcelRepository parcelRepository;

    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiYWRtaW4iXSwiaWF0IjoxNzA0NjIwNzY3LCJleHAiOjE3MDU0ODQ3Njd9.FibPwDo0ypeUHCU37Rempjb7FPWY9sMAy9P6Q6CMgxY";

    @Test
    public void when_ParcelDoesNotExistDuringCreation_Expect_Success() throws Exception {
        this.parcelRepository.deleteAll();

        this.saveTestUser();

        String json_userToCreate =
                "{\n  " +
                    "\"name\": \"Cloths\",\n  " +
                    "\"description\": \"Christmas Gift to my sister\"}";

        mockMvc
                .perform(post("/products")
                        .header("AUTHORIZATION", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_userToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Product creation successful."));
    }

//    @Test
//    public void when_ProductNameExistsDuringCreation_Expect_ErrorMessage() throws Exception {
//        this.productRepository.deleteAll();
//
//        this.saveTestUser();
//
//        Category category = this.categoryRepository.findById(1).orElse(null);
//
//        // Save test product
//        Product newProduct = new Product();
//        newProduct.setName("Green Puffer Jacket");
//        newProduct.setQuantity(2);
//        newProduct.setCategory(category);
//        newProduct.setAmount(100);
//        newProduct.setColor("Red");
//        newProduct.setDescription("This is the description");
//
//        this.productRepository.save(newProduct);
//
//        String json_userToCreate =
//                "{\n  " +
//                        "\"name\": \"Green Puffer Jacket\",\n  " +
//                        "\"quantity\": 10,\n  " +
//                        "\"amount\": 24.5,\n  " +
//                        "\"description\": \"This is the description for this green puffer jacket\",\n  " +
//                        "\"categoryId\": 1,\n  " +
//                        "\"color\": \"Green\"\n" +
//                        "}";
//
//        mockMvc
//                .perform(post("/products")
//                        .header("AUTHORIZATION", token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json_userToCreate)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.statusCode").value("400"))
//                .andExpect(jsonPath("$.message").value("Product with name already exists."));
//
//    }

    public void saveTestUser() {
        this.userRepository.deleteAll();

        Role role = this.roleRepository.findById(1).orElse(null);

        User newUser = new User();
        newUser.setName("Farouk");
        newUser.setEmail("faroukbakre@yahoo.com");
        String hashedPassword = passwordEncoder.encode("0000");
        newUser.setPassword(hashedPassword);
        newUser.setRole(role);
        this.userRepository.save(newUser);
    }
}
