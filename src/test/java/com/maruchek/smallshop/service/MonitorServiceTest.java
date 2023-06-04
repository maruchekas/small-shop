package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.api.response.MonitorResponse;
import com.maruchek.smallshop.api.response.MonitorShortResponse;
import com.maruchek.smallshop.model.Monitor;
import com.maruchek.smallshop.repository.MonitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MonitorServiceTest {

    private final MonitorService monitorService;
    private final MonitorRepository monitorRepository;
    private MonitorRequest monitorRequest;
    private Monitor monitor;


    @Autowired
    public MonitorServiceTest(MonitorService monitorService, MonitorRepository monitorRepository) {
        this.monitorService = monitorService;
        this.monitorRepository = monitorRepository;
    }

    @BeforeEach
    void beforeEach() {
        monitor = new Monitor();
        monitor.setSerialNumber("serialN");
        monitor.setPrice(500);
        monitor.setManufacturer("manufacturer");
        monitor.setScreenSize(27);
        monitor.setStockBalance(10);
        monitorRequest = new MonitorRequest();
        monitorRequest.setSerialNumber("serialN")
                .setManufacturer("manufacturer")
                .setPrice(500)
                .setStockBalance(10);
    }

    @Test
    void getAll() {
        // TODO: NotImplemented
        List<MonitorShortResponse> monitorShortResponseList = monitorService.getAll();
        int initialSize = monitorShortResponseList.size();

        monitorRepository.save(monitor);
        monitorShortResponseList = monitorService.getAll();
        assertEquals(initialSize + 1, monitorShortResponseList.size());

        Monitor monitor2 = new Monitor();
        monitor.setSerialNumber("serialN");
        monitor.setPrice(500);
        monitor.setManufacturer("manufacturer");
        monitor.setScreenSize(27);
        monitorRepository.save(monitor2);
        monitorShortResponseList = monitorService.getAll();
        assertEquals(initialSize + 2, monitorShortResponseList.size());
    }

    @Test
    void getById() {
        long id = monitorRepository.save(monitor).getId();
        MonitorResponse monitorResponse = monitorService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", monitorResponse.getManufacturer());
        assertEquals(10, monitorResponse.getStockBalance());
        assertEquals(500, monitorResponse.getPrice());
        assertEquals("serialN", monitorResponse.getSerialNumber());
    }

    @Test
    void addMonitor() {
        // TODO: NotImplemented
        MonitorResponse monitorResponse = monitorService.addMonitor(monitorRequest);
        long id = monitorResponse.getId();
        MonitorResponse monitorFromDb = monitorService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", monitorFromDb.getManufacturer());
        assertEquals(monitorResponse.getPrice(), monitorFromDb.getPrice());
    }

    @Test
    void updateMonitor() {
        // TODO: NotImplemented
        MonitorResponse monitorResponse = monitorService.addMonitor(monitorRequest);
        long id = monitorResponse.getId();
        MonitorResponse monitorFromDb = monitorService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", monitorFromDb.getManufacturer());
        assertEquals(monitorResponse.getPrice(), monitorFromDb.getPrice());

        monitorRequest = new MonitorRequest();
        monitorRequest.setManufacturer("new_manufacturer")
                .setPrice(600)
                .setStockBalance(20);
        MonitorResponse updatedMonitor = monitorService.updateMonitor(id, monitorRequest);
        assertEquals(id, updatedMonitor.getId());
        assertEquals("new_manufacturer", updatedMonitor.getManufacturer());
        assertEquals(600, updatedMonitor.getPrice());
        assertEquals(20, updatedMonitor.getStockBalance());
    }
}
