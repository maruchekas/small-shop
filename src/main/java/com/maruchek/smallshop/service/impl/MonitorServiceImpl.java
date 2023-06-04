package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.model.DesktopComputer;
import com.maruchek.smallshop.model.Monitor;
import com.maruchek.smallshop.model.mapper.DesktopPcMapper;
import com.maruchek.smallshop.model.mapper.Mapper;
import com.maruchek.smallshop.model.mapper.MonitorMapper;
import com.maruchek.smallshop.repository.MonitorRepository;
import com.maruchek.smallshop.service.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {

    private final MonitorRepository monitorRepository;


    @Override
    public MonitorResponse getById(long id) {
        final long balance = monitorRepository.count();
        Monitor monitor = checkEndGetMonitor(id);
        return MonitorMapper.toFullResponse(monitor)
                .setStockBalance(balance);
    }

    @Override
    public List<MonitorShortResponse> getAll() {
        List<Monitor> monitors = monitorRepository.findAll();
        return Mapper.convertList(monitors, MonitorMapper::toShortResponse);
    }

    @Override
    public MonitorResponse addMonitor(MonitorRequest request) {
        Monitor monitor = getNewMonitor(request);

        monitorRepository.save(monitor);
        return MonitorMapper.toFullResponse(monitor);
    }

    @Override
    public MonitorResponse updateMonitor(long id, MonitorRequest request) {
        Monitor monitor = checkEndGetMonitor(id);

        monitor.setPrice(request.getPrice())
                .setSerialNumber(request.getSerialNumber())
                .setManufacturer(request.getManufacturer())
                .setScreenSize(request.getScreenSize());

        monitorRepository.save(monitor);

        return MonitorMapper.toFullResponse(monitor);
    }

    private Monitor checkEndGetMonitor(long id) {
        return monitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Monitor with id %s not found", id)));
    }

    private Monitor getNewMonitor(MonitorRequest request) {
        long balance = monitorRepository.count();
        Monitor monitor = new Monitor();
        monitor.setPrice(request.getPrice());
        monitor.setManufacturer(request.getManufacturer());
        monitor.setSerialNumber(request.getSerialNumber());
        monitor.setScreenSize(monitor.getScreenSize());
        monitor.setStockBalance(++balance);

        return monitor;
    }
}
