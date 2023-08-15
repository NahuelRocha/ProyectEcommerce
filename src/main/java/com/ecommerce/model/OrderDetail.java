package com.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_order_details")
    private String orderId;

    @Min(value = 0, message = "The detail price must be greater than zero")
    @Column(name = "detail_price")
    private Double detailPrice;

    @NotNull(message = "The quantity of products cannot be zero")
    @Column(name = "quantity")
    private Integer detailQuantity;

    @NotNull(message = "The total cannot be zero")
    @Column(name = "total_price")
    private Double totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private Purchase purchase;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
