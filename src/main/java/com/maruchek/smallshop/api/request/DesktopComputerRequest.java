package com.maruchek.smallshop.api.request;

import com.maruchek.smallshop.config.validator.ValueOfEnum;
import com.maruchek.smallshop.enums.FormFactor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DesktopComputerRequest extends BaseRequest {

    @ValueOfEnum(enumClass = FormFactor.class)
    private String formFactor;

}
