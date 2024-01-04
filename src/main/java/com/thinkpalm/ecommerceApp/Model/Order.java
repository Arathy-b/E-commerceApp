package com.thinkpalm.ecommerceApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="custOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private float totalPrice;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Timestamp order_date;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String transactionId;

}