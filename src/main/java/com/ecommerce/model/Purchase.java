package com.ecommerce.model;

import com.ecommerce.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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

    @Column(name = "id_order")
    private String orderId;

    private LocalDateTime date;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @NotBlank(message = "Enter the address")
    @Column(name = "shipping_address")
    private String shippingAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "purchase")
    private List<OrderDetail> orderDetail = new ArrayList<>();

}
