package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.GetAllUserRequest;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<UserDTO> getAllUsers(GetAllUserRequest getAllUserRequest) {

        Pageable pageable = PageRequest.of(getAllUserRequest.page(), getAllUserRequest.size(), Sort.by("firstName").ascending());

        Page<User> pageUser = userRepository.findAll(pageable);

        List<User> allUsers = pageUser.getContent();

        if (allUsers.isEmpty()) {

            throw new ResourceNotFoundException("There are no users to show ");

        }

        return allUsers.stream().map(Mappers::userToUserDTO).collect(Collectors.toList());

    }

    @Override
    public UserDTO getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return Mappers.userToUserDTO(user);
    }

    @Override
    public String deleteUser(Long id) {

        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        List<Purchase> findPurchase = purchaseRepository.findByUserName(findUser.getUsername());

        List<OrderDetail> orderDetails = findPurchase.stream()
                .flatMap(purchase -> orderDetailRepository.findByPurchase(purchase).stream())
                .toList();

        orderDetails.stream().map(OrderDetail::getId)
                .forEach(orderDetailRepository::deleteById);

        findPurchase.stream().map(Purchase::getId)
                .forEach(purchaseRepository::deleteById);


        userRepository.deleteById(id);

        return "The user has been successfully deleted";
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateRequest userUpdate) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        Mappers.mapUser(user, userUpdate);

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

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No user by that name: " + firstName);
        }

        return users.stream().map(Mappers::userToUserDTO).toList();
    }

    @Override
    public UserDTO findByEmail(String email) {

        User existingEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));

        return Mappers.userToUserDTO(existingEmail);
    }


}
