package com.maruchek.smallshop.api.request;

import com.maruchek.smallshop.enums.FormFactor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class DesktopComputerRequest {

    private int serialNumber;
    @NotEmpty(message = "Field is empty")
    private String manufacturer;
    private double price;
    private FormFactor formFactor;

}
