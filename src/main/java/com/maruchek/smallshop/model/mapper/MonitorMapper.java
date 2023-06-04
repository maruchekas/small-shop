package com.maruchek.smallshop.model.mapper;

import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.Monitor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonitorMapper {

    public static MonitorShortResponse toShortResponse(Monitor monitor) {
        return new MonitorShortResponse().setScreenSize(monitor.getScreenSize())
                .setManufacturer(monitor.getManufacturer())
                .setPrice(monitor.getPrice());
    }

    public static MonitorResponse toFullResponse(Monitor monitor) {
        return new MonitorResponse()
                .setId(monitor.getId())
                .setSerialNumber(monitor.getSerialNumber())
                .setManufacturer(monitor.getManufacturer())
                .setPrice(monitor.getPrice())
                .setScreenSize(monitor.getScreenSize())
                .setStockBalance(monitor.getStockBalance());
    }

    public static Monitor toMonitor(MonitorRequest request) {
        Monitor monitor = new Monitor();
        monitor.setSerialNumber(request.getSerialNumber());
        monitor.setManufacturer(request.getManufacturer());
        monitor.setScreenSize(request.getScreenSize());
        monitor.setPrice(request.getPrice());
        return monitor;
    }
}
