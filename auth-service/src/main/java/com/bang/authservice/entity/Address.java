package com.bang.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private  String ward;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

}
