package com.maruchek.smallshop.service;

import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.api.response.LaptopResponse;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.model.Laptop;
import com.maruchek.smallshop.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LaptopServiceTest {

    private final LaptopService laptopService;
    private final LaptopRepository laptopRepository;
    private LaptopRequest laptopRequest;
    private Laptop laptop;


    @Autowired
    public LaptopServiceTest(LaptopService laptopService, LaptopRepository laptopRepository) {
        this.laptopService = laptopService;
        this.laptopRepository = laptopRepository;
    }

    @BeforeEach
    void beforeEach() {
        laptop = new Laptop();
        laptop.setSerialNumber("serialN");
        laptop.setPrice(1000);
        laptop.setManufacturer("manufacturer");
        laptop.setSize(16);
        laptop.setStockBalance(10);
        laptopRequest = new LaptopRequest();
        laptopRequest.setSerialNumber("serialN")
                .setManufacturer("manufacturer")
                .setPrice(1000)
                .setStockBalance(10);
    }

    @Test
    void getAll() {
        // TODO: NotImplemented
        List<LaptopShortResponse> laptopShortResponseList = laptopService.getAll();
        int initialSize = laptopShortResponseList.size();

        laptopRepository.save(laptop);
        laptopShortResponseList = laptopService.getAll();
        assertEquals(initialSize + 1, laptopShortResponseList.size());

        Laptop laptop2 = new Laptop();
        laptop.setSerialNumber("serialN");
        laptop.setPrice(1000);
        laptop.setManufacturer("manufacturer");
        laptop.setSize(16);
        laptopRepository.save(laptop2);
        laptopShortResponseList = laptopService.getAll();
        assertEquals(initialSize + 2, laptopShortResponseList.size());
    }

    @Test
    void getById() {
        long id = laptopRepository.save(laptop).getId();
        LaptopResponse laptopResponse = laptopService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", laptopResponse.getManufacturer());
        assertEquals(10, laptopResponse.getStockBalance());
        assertEquals(1000, laptopResponse.getPrice());
        assertEquals("serialN", laptopResponse.getSerialNumber());
    }

    @Test
    void addLaptop() {
        // TODO: NotImplemented
        LaptopResponse laptopResponse = laptopService.addLaptop(laptopRequest);
        long id = laptopResponse.getId();
        LaptopResponse laptopFromDb = laptopService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", laptopFromDb.getManufacturer());
        assertEquals(laptopResponse.getPrice(), laptopFromDb.getPrice());
    }

    @Test
    void updateLaptop() {
        // TODO: NotImplemented
        LaptopResponse laptopResponse = laptopService.addLaptop(laptopRequest);
        long id = laptopResponse.getId();
        LaptopResponse laptopFromDb = laptopService.getById(id);
        assertTrue(id != 0);
        assertEquals("manufacturer", laptopFromDb.getManufacturer());
        assertEquals(laptopResponse.getPrice(), laptopFromDb.getPrice());

        laptopRequest = new LaptopRequest();
        laptopRequest.setManufacturer("new_manufacturer")
                .setPrice(1200)
                .setStockBalance(20);
        LaptopResponse updatedLaptop = laptopService.updateLaptop(id, laptopRequest);
        assertEquals(id, updatedLaptop.getId());
        assertEquals("new_manufacturer", updatedLaptop.getManufacturer());
        assertEquals(1200, updatedLaptop.getPrice());
        assertEquals(20, updatedLaptop.getStockBalance());
    }
}
