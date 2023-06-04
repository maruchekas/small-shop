package com.maruchek.smallshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "monitors")
public class HardDrive {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_number")
    private int serialNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private double price;

    @Column(name = "stock_balance")
    private int stockBalance;

    @Column(name = "capacity")
    private int capacity;
}
