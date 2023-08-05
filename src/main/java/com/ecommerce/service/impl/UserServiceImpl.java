package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDTO createUser(User user) {

        Optional<User> existingUsername = userRepository.findByUsername(user.getUsername());

        if (existingUsername.isEmpty()){

            User newUser = new User();

            newUser.setUsername(user.getUsername());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPhone(user.getPhone());
            newUser.setRole(user.getRole());
            newUser.setPassword(user.getPassword());
            newUser.setPurchase(new ArrayList<>());

            userRepository.save(newUser);

            return Mappers.userToUserDTO(newUser);

        } else throw new IllegalArgumentException("The username already exists in the database");

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

    @Override
    public UserDTO updateUser(Long id, User userUpdate) {

        User user =  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " + id));

        Mappers.mapUser(user , userUpdate);

        userRepository.save(user);

        return Mappers.userToUserDTO(user);

    }

    @Override
    public UserDTO findByUsername(String username) {

        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return Mappers.userToUserDTO(findUser);
    }

    @Override
    public List<UserDTO> findByFirstName(String firstName) {

        List<User> users = userRepository.findByFirstName(firstName);

        if(users.isEmpty()){
            throw new ResourceNotFoundException("No user by that name: " + firstName);
        }

        return users.stream().map(Mappers::userToUserDTO).toList();
    }


}
