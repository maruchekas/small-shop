package com.maruchek.smallshop.api.response;

import com.maruchek.smallshop.enums.FormFactor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DesktopPcShortResponse {

    private String manufacturer;
    private double price;
    private FormFactor formFactor;

}
