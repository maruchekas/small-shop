package com.maruchek.smallshop.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseEntity implements Serializable {

    private int serialNumber;
    private String manufacturer;
    private double price;
    private long stockBalance;

}
