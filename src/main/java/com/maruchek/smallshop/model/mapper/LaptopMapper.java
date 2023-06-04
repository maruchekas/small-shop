package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.model.Laptop;
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
                .setSize(laptop.getSize())
                .setStockBalance(laptop.getStockBalance());
    }

    public static Laptop toLaptop(LaptopRequest request) {
        Laptop laptop = new Laptop();
        laptop.setSerialNumber(request.getSerialNumber());
        laptop.setManufacturer(request.getManufacturer());
        laptop.setSize(request.getSize());
        laptop.setPrice(request.getPrice());
        laptop.setStockBalance(request.getStockBalance());

        return laptop;
    }
}
