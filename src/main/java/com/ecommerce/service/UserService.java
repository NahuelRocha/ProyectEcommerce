package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(User user);

    List<UserDTO> getAllUsers();

    UserDTO getUser(Long id);

    String deleteUser(Long id);



}
