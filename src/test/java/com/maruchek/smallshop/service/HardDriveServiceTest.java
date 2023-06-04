package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.api.response.HardDriveResponse;
import com.maruchek.smallshop.api.response.HardDriveShortResponse;
import com.maruchek.smallshop.model.HardDrive;
import com.maruchek.smallshop.repository.HardDriveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HardDriveServiceTest {

    private final HardDriveService hardDriveService;
    private final HardDriveRepository hardDriveRepository;
    private HardDriveRequest hardDriveRequest;
    private HardDrive hardDrive;


    @Autowired
    public HardDriveServiceTest(HardDriveService hardDriveService, HardDriveRepository hardDriveRepository) {
        this.hardDriveService = hardDriveService;
        this.hardDriveRepository = hardDriveRepository;
    }

    @BeforeEach
    void beforeEach() {
        hardDrive = new HardDrive();
        hardDrive.setSerialNumber("serialN");
        hardDrive.setPrice(300);
        hardDrive.setManufacturer("manufacturer");
        hardDrive.setCapacity(2000);
        hardDrive.setStockBalance(10);

        hardDriveRequest = new HardDriveRequest();
        hardDriveRequest.setCapacity(2000);
        hardDriveRequest.setSerialNumber("serialN")
                .setManufacturer("manufacturer")
                .setPrice(300)
                .setStockBalance(10);
    }

    @Test
    void getAll() {
        // TODO: NotImplemented
        List<HardDriveShortResponse> hardDriveShortResponseList = hardDriveService.getAll();
        int initialSize = hardDriveShortResponseList.size();

        hardDriveRepository.save(hardDrive);
        hardDriveShortResponseList = hardDriveService.getAll();
        assertEquals(initialSize + 1, hardDriveShortResponseList.size());

        HardDrive hardDrive2 = new HardDrive();
        hardDrive.setSerialNumber("serialN");
        hardDrive.setPrice(300);
        hardDrive.setManufacturer("manufacturer");
        hardDrive.setCapacity(2000);
        hardDriveRepository.save(hardDrive2);
        hardDriveShortResponseList = hardDriveService.getAll();
        assertEquals(initialSize + 2, hardDriveShortResponseList.size());
    }

    @Test
    void getById() {
        long id = hardDriveRepository.save(hardDrive).getId();
        HardDriveResponse hardDriveResponse = hardDriveService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", hardDriveResponse.getManufacturer());
        assertEquals(10, hardDriveResponse.getStockBalance());
        assertEquals(300, hardDriveResponse.getPrice());
        assertEquals("serialN", hardDriveResponse.getSerialNumber());
    }

    @Test
    void addHardDrive() {
        // TODO: NotImplemented
        HardDriveResponse hardDriveResponse = hardDriveService.addHardDrive(hardDriveRequest);
        long id = hardDriveResponse.getId();
        HardDriveResponse hardDriveFromDb = hardDriveService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", hardDriveFromDb.getManufacturer());
        assertEquals(2000, hardDriveFromDb.getCapacity());
        assertEquals(10, hardDriveFromDb.getStockBalance());
        assertEquals(hardDriveResponse.getPrice(), hardDriveFromDb.getPrice());
    }

    @Test
    void updateHardDrive() {
        // TODO: NotImplemented
        HardDriveResponse hardDriveResponse = hardDriveService.addHardDrive(hardDriveRequest);
        long id = hardDriveResponse.getId();
        HardDriveResponse hardDriveFromDb = hardDriveService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", hardDriveFromDb.getManufacturer());
        assertEquals(hardDriveResponse.getPrice(), hardDriveFromDb.getPrice());

        hardDriveRequest = new HardDriveRequest();
        hardDriveRequest.setCapacity(3000);
        hardDriveRequest.setManufacturer("new_manufacturer")
                .setPrice(400)
                .setStockBalance(20);
        HardDriveResponse updatedHardDrive = hardDriveService.updateHardDrive(id, hardDriveRequest);
        assertEquals(id, updatedHardDrive.getId());
        assertEquals("new_manufacturer", updatedHardDrive.getManufacturer());
        assertEquals(400, updatedHardDrive.getPrice());
        assertEquals(3000, updatedHardDrive.getCapacity());
        assertEquals(20, updatedHardDrive.getStockBalance());
    }
}
