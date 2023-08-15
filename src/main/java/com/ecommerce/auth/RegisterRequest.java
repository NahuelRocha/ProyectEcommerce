package com.ecommerce.auth;


import jakarta.validation.constraints.Email;
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
public class RegisterRequest {

    @NotBlank(message = "The user name can not be empty")
    private String username;

    @NotBlank(message = "The first name can not be empty")
    private String firstname;

    @NotBlank(message = "The last name can not be empty")
    private String lastname;

    @Email(message = "The given email format is not valid")
    @NotBlank(message = "The email can not be empty")
    private String email;

    @Size(min = 4, max = 18)
    @NotBlank(message = "The phone number can not be empty")
    private String phone;

    @Size(min = 8, message = "The password must be at least 8 characters")
    @NotBlank(message = "The password can not be empty")
    private String password;

}
