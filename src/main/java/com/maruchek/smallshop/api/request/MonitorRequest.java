package com.maruchek.smallshop.api.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class MonitorRequest {

    @Pattern(regexp = "[0-9a-zA-Z]{4,10}") //should be a hexadecimal number
    private String serialNumber;
    @NotEmpty(message = "Field is empty")
    private String manufacturer;
    @DecimalMin("0.1")
    private double price;
    @DecimalMin("12")
    private double screenSize;

}
