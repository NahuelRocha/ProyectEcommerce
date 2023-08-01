package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_details_id")
    private Long orderId;

    private Long productId;

    private double detailSoldPrice;

    private int detailSoldQuantity;

    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;


}
