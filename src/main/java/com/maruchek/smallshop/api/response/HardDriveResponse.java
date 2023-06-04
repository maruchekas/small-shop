package com.maruchek.smallshop.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HardDriveResponse {

    long id;
    private String serialNumber;
    private String manufacturer;
    private double price;
    private int capacity;
    private long stockBalance;

}
