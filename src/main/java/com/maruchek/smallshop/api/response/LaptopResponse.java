package com.maruchek.smallshop.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LaptopResponse {

    long id;
    private String serialNumber;
    private String manufacturer;
    private double price;
    private double size;
    private long stockBalance;

}
