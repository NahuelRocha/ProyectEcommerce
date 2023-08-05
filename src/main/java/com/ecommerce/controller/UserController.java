package com.ecommerce.controller;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;
import com.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        System.out.println(user);

        UserDTO newUser = userService.createUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> userList = userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {

        UserDTO user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        String message = userService.deleteUser(id);

        return ResponseEntity.ok(message);

    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User userUpdate) {

        UserDTO user = userService.updateUser(id, userUpdate);

        return ResponseEntity.ok(user);

    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> findByUserName(@PathVariable("username") String username) {

        UserDTO findUser = userService.findByUsername(username);

        return ResponseEntity.ok(findUser);

    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<UserDTO>> findByFirstName(@PathVariable("name") String firstName) {

        List<UserDTO> users = userService.findByFirstName(firstName);

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

}
