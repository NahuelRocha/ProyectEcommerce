package com.ecommerce.dto;

public record ProductDTO(

        Long id,
        String name,

        Integer stock,

        String description,

        Double price

) {}
