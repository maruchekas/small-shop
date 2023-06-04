package com.maruchek.smallshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum FormFactor {

    DESKTOP("DESKTOP"),
    NET_TOP("NET_TOP"),
    MONO_BLOCK("MONO_BLOCK");

    private final String value;

}
