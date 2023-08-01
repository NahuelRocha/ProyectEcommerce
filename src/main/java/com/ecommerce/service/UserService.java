package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(User user);

    List<UserDTO> getAllUsers();

    UserDTO getUser(Long id);

    String deleteUser(Long id);

    UserDTO updateUser(Long id ,User user);

    UserDTO findByUsername (String username);

    List<UserDTO> findByFirstName(String firstName);

}
