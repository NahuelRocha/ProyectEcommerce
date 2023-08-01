package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "total_price")
    private double totalPrice;


    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "purchase")
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
