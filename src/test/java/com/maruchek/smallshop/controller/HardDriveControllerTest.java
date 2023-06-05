package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
import com.maruchek.smallshop.api.request.HardDriveRequest;
import com.maruchek.smallshop.repository.BaseEntityRepository;
import com.maruchek.smallshop.repository.HardDriveRepository;
import com.maruchek.smallshop.service.HardDriveService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class HardDriveControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HardDriveService hardDriveService;

    @Autowired
    private BaseEntityRepository baseRepository;

    @Autowired
    private HardDriveRepository hardDriveRepository;

    private HardDriveRequest hardDrive1;
    private HardDriveRequest hardDrive2;

    @BeforeEach
    public void setup() {

        hardDrive1 = new HardDriveRequest();
        hardDrive1.setCapacity(1000);
        hardDrive1.setManufacturer("Manufacturer1");
        hardDrive1.setSerialNumber("SerialN1");
        hardDrive1.setPrice(300);
        hardDrive1.setStockBalance(10);

        hardDrive2 = new HardDriveRequest();
        hardDrive2.setCapacity(1000);
        hardDrive2.setManufacturer("Manufacturer2");
        hardDrive2.setSerialNumber("SerialN2");
        hardDrive2.setPrice(300);
        hardDrive2.setStockBalance(20);

        hardDriveService.addHardDrive(hardDrive1);
        hardDriveService.addHardDrive(hardDrive2);

    }

    @AfterEach
    public void clean() {
        baseRepository.deleteAll();
        hardDriveRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/hdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void getById() throws Exception {
        long id = hardDriveRepository.findAll().get(0).getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/hdd/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(300))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createHardDrive() throws Exception {

        HardDriveRequest hardDrive = new HardDriveRequest();
        hardDrive.setCapacity(1000);
        hardDrive.setManufacturer("Manufacturer");
        hardDrive.setSerialNumber("SerialN1");
        hardDrive.setPrice(1500);
        hardDrive.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/hdd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(hardDrive))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateHardDrive() throws Exception {
        long id = hardDriveRepository.findAll().get(0).getId();

        HardDriveRequest hardDrive = new HardDriveRequest();
        hardDrive.setCapacity(1500);
        hardDrive.setManufacturer("Manufacturer");
        hardDrive.setSerialNumber("SerialN1");
        hardDrive.setPrice(500);
        hardDrive.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/hdd/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(hardDrive))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.capacity").value(1500))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
