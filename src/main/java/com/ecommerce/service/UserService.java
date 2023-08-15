package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.GetAllUserRequest;
import com.ecommerce.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers(GetAllUserRequest getAllUserRequest);

    UserDTO getUser(Long id);

    String deleteUser(Long id);

    UserDTO updateUser(Long id ,User user);

    UserDTO findByUsername (String username);

    List<UserDTO> findByFirstName(String firstName);

    UserDTO findByEmail(String email);

}
