package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name of the product is missing")
    private String name;

    @Min(value = 0, message = "Stock cannot be less than zero")
    private Integer stock;

    @NotBlank(message = "The product description is missing")
    private String description;

    @NotNull(message = "This field cannot be null")
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetail = new ArrayList<>();

}
