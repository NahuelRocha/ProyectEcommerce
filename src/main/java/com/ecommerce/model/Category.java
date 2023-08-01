package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table( name = "category" )
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "The category name can not be empty" )
    private String name;

    @NotBlank( message = "The category description can not be empty" )
    @Column( nullable = false )
    private String description;

}

