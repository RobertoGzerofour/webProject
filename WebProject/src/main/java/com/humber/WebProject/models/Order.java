package com.humber.WebProject.models;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<ProductItem> product;


}
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Address {
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;
}
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String artist;

    private String imageLink;
}

