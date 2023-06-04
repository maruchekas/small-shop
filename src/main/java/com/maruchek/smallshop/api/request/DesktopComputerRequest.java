package com.maruchek.smallshop.api.request;

import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.validator.ValueOfEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class DesktopComputerRequest {

    @Pattern(regexp = "[0-9a-zA-Z]{4,10}") //should be a hexadecimal number
    private String serialNumber;
    @NotEmpty(message = "Field is empty")
    private String manufacturer;
    @DecimalMin("0.1")
    private double price;
    @ValueOfEnum(enumClass = FormFactor.class)
    private String formFactor;

}
