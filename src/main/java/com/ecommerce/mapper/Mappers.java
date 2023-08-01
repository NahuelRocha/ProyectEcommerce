package com.ecommerce.mapper;


import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.Purchase;
import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.model.Category;
import com.ecommerce.model.User;

public class Mappers {


    public static UserDTO userToUserDTO(User user) {

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getPurchases()
        );
    }

    public static void mapUser(User oldUser, User updateUser) {

        oldUser.setUsername(updateUser.getUsername());
        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setPhone(updateUser.getPhone());
        oldUser.setRole(updateUser.getRole());
        oldUser.setPurchases(updateUser.getPurchases());

    }


    public static PurchaseDTO purchaseToPurchaseDTO(Purchase purchase){

        return new PurchaseDTO(
                purchase.getId(),
                purchase.getDate(),
                purchase.getTotalPrice(),
                purchase.getUserName(),
                purchase.getOrderDetails()
        );

    }

    public static Purchase mapPurchase(Purchase purchase) {

        return null;


    }


    public static CategoryDTO categoryToCategoryDTO( Category category ) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}
