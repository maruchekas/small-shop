package com.maruchek.smallshop.service.impl;

import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.Monitor;
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
    private final MonitorMapper mapper;


    @Override
    public MonitorResponse getById(long id) {
        Monitor monitor = checkEndGetMonitor(id);
        return mapper.toFullResponse(monitor);
    }

    @Override
    public List<MonitorShortResponse> getAll() {
        List<Monitor> monitors = monitorRepository.findAll();
        return mapper.toListShortResponse(monitors);
    }

    @Override
    public MonitorResponse addMonitor(MonitorRequest request) {
        Monitor monitor = getNewMonitor(request);

        monitorRepository.save(monitor);
        return mapper.toFullResponse(monitor);
    }

    @Override
    public MonitorResponse updateMonitor(long id, MonitorRequest request) {
        Monitor monitor = checkEndGetMonitor(id);

        monitor.setPrice(request.getPrice());
        monitor.setSerialNumber(request.getSerialNumber());
        monitor.setManufacturer(request.getManufacturer());
        monitor.setScreenSize(request.getScreenSize());
        monitor.setStockBalance(request.getStockBalance());

        monitorRepository.save(monitor);

        return mapper.toFullResponse(monitor);
    }

    private Monitor checkEndGetMonitor(long id) {
        return monitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Monitor with id %s not found", id)));
    }

    private Monitor getNewMonitor(MonitorRequest request) {
        Monitor monitor = new Monitor();
        monitor.setPrice(request.getPrice());
        monitor.setManufacturer(request.getManufacturer());
        monitor.setSerialNumber(request.getSerialNumber());
        monitor.setScreenSize(request.getScreenSize());
        monitor.setStockBalance(request.getStockBalance());

        return monitor;
    }
}
