package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDTO createUser(User user) {

        User newUser = new User();
        List<Purchase> purchase = new ArrayList<>();

        newUser.setId(user.getId());
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setRole(user.getRole());
        newUser.setPassword(user.getPassword());
        newUser.setPurchases(purchase);

        userRepository.save(newUser);

        return Mappers.userToUserDTO(newUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream()
                .map(Mappers::userToUserDTO).collect(Collectors.toList());

    }
    @Override
    public UserDTO getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return Mappers.userToUserDTO(user);
    }

    @Override
    public String deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " + id));

        userRepository.deleteById(id);

        return "The user has been successfully deleted";
    }


}
