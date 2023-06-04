package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.model.DesktopComputer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DesktopPcMapper {

    public static DesktopPcShortResponse toShortResponse(DesktopComputer computer) {
        return new DesktopPcShortResponse().setFormFactor(computer.getFormFactor())
                .setManufacturer(computer.getManufacturer())
                .setPrice(computer.getPrice());
    }

    public static DesktopComputerResponse toFullResponse(DesktopComputer computer) {
        return new DesktopComputerResponse()
                .setId(computer.getId())
                .setManufacturer(computer.getManufacturer())
                .setPrice(computer.getPrice())
                .setFormFactor(computer.getFormFactor());
    }

    public static DesktopComputer toDesktopPc(DesktopComputerRequest request) {
        return new DesktopComputer()
                .setSerialNumber(request.getSerialNumber())
                .setManufacturer(request.getManufacturer())
                .setFormFactor(request.getFormFactor())
                .setPrice(request.getPrice());
    }
}
