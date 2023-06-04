package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.Laptop;
import com.maruchek.smallshop.model.Monitor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LaptopMapper {

    public static LaptopShortResponse toShortResponse(Laptop laptop) {
        return new LaptopShortResponse().setSize(laptop.getSize())
                .setManufacturer(laptop.getManufacturer())
                .setPrice(laptop.getPrice());
    }

    public static LaptopResponse toFullResponse(Laptop laptop) {
        return new LaptopResponse()
                .setId(laptop.getId())
                .setSerialNumber(laptop.getSerialNumber())
                .setManufacturer(laptop.getManufacturer())
                .setPrice(laptop.getPrice())
                .setSize(laptop.getSize());
    }

    public static Laptop toLaptop(LaptopRequest request) {
        return new Laptop()
                .setSerialNumber(request.getSerialNumber())
                .setManufacturer(request.getManufacturer())
                .setSize(request.getSize())
                .setPrice(request.getPrice());
    }
}
