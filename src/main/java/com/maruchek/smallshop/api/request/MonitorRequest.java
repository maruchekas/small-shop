package com.maruchek.smallshop.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@Accessors(chain = true)
public class MonitorRequest extends BaseRequest{

    @DecimalMin("12")
    private double screenSize;

}
