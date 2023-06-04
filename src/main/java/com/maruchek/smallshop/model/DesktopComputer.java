package com.maruchek.smallshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maruchek.smallshop.enums.FormFactor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class DesktopComputer extends BaseEntity {

    @Column(name = "form_factor")
    private FormFactor formFactor;
}
