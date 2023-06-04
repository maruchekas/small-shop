package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.enums.FormFactor;
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
                .setSerialNumber(computer.getSerialNumber())
                .setManufacturer(computer.getManufacturer())
                .setPrice(computer.getPrice())
                .setFormFactor(computer.getFormFactor())
                .setStockBalance(computer.getStockBalance());
    }

    public static DesktopComputer toDesktopPc(DesktopComputerRequest request) {
        DesktopComputer computer = new DesktopComputer();
        computer.setSerialNumber(request.getSerialNumber());
        computer.setManufacturer(request.getManufacturer());
        computer.setFormFactor(FormFactor.valueOf(request.getFormFactor()));
        computer.setPrice(request.getPrice());
        computer.setStockBalance(request.getStockBalance());

        return computer;
    }
}
