package com.maruchek.smallshop.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LaptopShortResponse {

    private String manufacturer;
    private double price;
    private double size;

}
