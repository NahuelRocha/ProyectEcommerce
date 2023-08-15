package com.ecommerce.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "The user name can not be empty")
    private String username;

    @Size(min = 8, message = "The password must be at least 8 characters")
    @NotBlank(message = "The password can not be empty")
    private String password;

}
