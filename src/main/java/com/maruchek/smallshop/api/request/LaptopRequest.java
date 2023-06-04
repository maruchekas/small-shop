package com.maruchek.smallshop.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Getter
@Setter
@Accessors(chain = true)
public class LaptopRequest extends BaseRequest {

    @Min(10)
    private int size;

}
