package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.model.HardDrive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HardDriveMapper {

    public static HardDriveShortResponse toShortResponse(HardDrive hardDrive) {
        return new HardDriveShortResponse().setCapacity(hardDrive.getCapacity())
                .setManufacturer(hardDrive.getManufacturer())
                .setPrice(hardDrive.getPrice());
    }

    public static HardDriveResponse toFullResponse(HardDrive hardDrive) {
        return new HardDriveResponse()
                .setId(hardDrive.getId())
                .setSerialNumber(hardDrive.getSerialNumber())
                .setManufacturer(hardDrive.getManufacturer())
                .setPrice(hardDrive.getPrice())
                .setCapacity(hardDrive.getCapacity());
    }

    public static HardDrive toHardDrive(HardDriveRequest request) {
        HardDrive hardDrive = new HardDrive();
        hardDrive.setSerialNumber(request.getSerialNumber());
        hardDrive.setManufacturer(request.getManufacturer());
        hardDrive.setCapacity(request.getCapacity());
        hardDrive.setPrice(request.getPrice());
        hardDrive.setStockBalance(request.getStockBalance());

        return hardDrive;
    }
}
