package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The user name can not be empty")
    @Column(name = "user_name" , nullable = false)
    private String username;


    @NotBlank(message = "The first name can not be empty")
    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @NotBlank(message = "The last name can not be empty")
    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Email(message = "The given email format is not valid")
    @NotBlank(message = "The email can not be empty")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "The phone number can not be empty")
    @Column(nullable = false)
    private String phone;


    @Size(min = 8, message = "The password must be at least 8 characters")
    @NotBlank(message = "The password can not be empty")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "The role can not be empty")
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

}

