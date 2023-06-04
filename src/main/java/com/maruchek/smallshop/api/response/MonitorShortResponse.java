package com.maruchek.smallshop.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MonitorShortResponse {

    private String manufacturer;
    private double price;
    private double screenSize;

}
