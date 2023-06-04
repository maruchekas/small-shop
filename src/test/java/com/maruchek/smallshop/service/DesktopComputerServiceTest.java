package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.api.response.DesktopComputerResponse;
import com.maruchek.smallshop.api.response.DesktopPcShortResponse;
import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.model.DesktopComputer;
import com.maruchek.smallshop.repository.DesktopComputerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DesktopComputerServiceTest {

    private final DesktopComputerService desktopComputerService;
    private final DesktopComputerRepository desktopComputerRepository;
    private DesktopComputerRequest desktopComputerRequest;
    private DesktopComputer desktopComputer;


    @Autowired
    public DesktopComputerServiceTest(DesktopComputerService desktopComputerService, DesktopComputerRepository desktopComputerRepository) {
        this.desktopComputerService = desktopComputerService;
        this.desktopComputerRepository = desktopComputerRepository;
    }

    @BeforeEach
    void beforeEach() {
        desktopComputer = new DesktopComputer();
        desktopComputer.setSerialNumber("serialN");
        desktopComputer.setPrice(500);
        desktopComputer.setManufacturer("manufacturer");
        desktopComputer.setFormFactor(FormFactor.DESKTOP);
        desktopComputer.setStockBalance(10);
        desktopComputerRequest = new DesktopComputerRequest();
        desktopComputerRequest.setFormFactor(FormFactor.DESKTOP.getValue());
        desktopComputerRequest.setSerialNumber("serialN")
                .setManufacturer("manufacturer")
                .setPrice(500)
                .setStockBalance(10);
    }

    @Test
    void getAll() {
        // TODO: NotImplemented
        List<DesktopPcShortResponse> desktopComputerShortResponseList = desktopComputerService.getAll();
        int initialSize = desktopComputerShortResponseList.size();

        desktopComputerRepository.save(desktopComputer);
        desktopComputerShortResponseList = desktopComputerService.getAll();
        assertEquals(initialSize + 1, desktopComputerShortResponseList.size());

        DesktopComputer desktopComputer2 = new DesktopComputer();
        desktopComputer.setSerialNumber("serialN");
        desktopComputer.setPrice(500);
        desktopComputer.setManufacturer("manufacturer");
        desktopComputer.setFormFactor(FormFactor.DESKTOP);
        desktopComputerRepository.save(desktopComputer2);
        desktopComputerShortResponseList = desktopComputerService.getAll();
        assertEquals(initialSize + 2, desktopComputerShortResponseList.size());
    }

    @Test
    void getById() {
        long id = desktopComputerRepository.save(desktopComputer).getId();
        DesktopComputerResponse desktopComputerResponse = desktopComputerService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", desktopComputerResponse.getManufacturer());
        assertEquals(10, desktopComputerResponse.getStockBalance());
        assertEquals(500, desktopComputerResponse.getPrice());
        assertEquals("serialN", desktopComputerResponse.getSerialNumber());
    }

    @Test
    void addDesktopComputer() {
        // TODO: NotImplemented
        DesktopComputerResponse desktopComputerResponse = desktopComputerService.addComputer(desktopComputerRequest);
        long id = desktopComputerResponse.getId();
        DesktopComputerResponse desktopComputerFromDb = desktopComputerService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", desktopComputerFromDb.getManufacturer());
        assertEquals(desktopComputerResponse.getPrice(), desktopComputerFromDb.getPrice());
    }

    @Test
    void updateDesktopComputer() {
        // TODO: NotImplemented
        DesktopComputerResponse desktopComputerResponse = desktopComputerService.addComputer(desktopComputerRequest);
        long id = desktopComputerResponse.getId();
        DesktopComputerResponse desktopComputerFromDb = desktopComputerService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", desktopComputerFromDb.getManufacturer());
        assertEquals(desktopComputerResponse.getPrice(), desktopComputerFromDb.getPrice());

        desktopComputerRequest = new DesktopComputerRequest();
        desktopComputerRequest.setFormFactor("MONO_BLOCK");
        desktopComputerRequest.setManufacturer("new_manufacturer")
                .setPrice(600)
                .setStockBalance(20);
        DesktopComputerResponse updatedDesktopComputer = desktopComputerService.updateComputer(id, desktopComputerRequest);
        assertEquals(id, updatedDesktopComputer.getId());
        assertEquals("new_manufacturer", updatedDesktopComputer.getManufacturer());
        assertEquals(600, updatedDesktopComputer.getPrice());
        assertEquals(FormFactor.MONO_BLOCK, updatedDesktopComputer.getFormFactor());
        assertEquals(20, updatedDesktopComputer.getStockBalance());
    }
}
