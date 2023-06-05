package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.repository.BaseEntityRepository;
import com.maruchek.smallshop.repository.MonitorRepository;
import com.maruchek.smallshop.service.MonitorService;
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
public class MonitorControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private BaseEntityRepository baseRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    private MonitorRequest monitor1;
    private MonitorRequest monitor2;

    @BeforeEach
    public void setup() {

        monitor1 = new MonitorRequest();
        monitor1.setScreenSize(27);
        monitor1.setManufacturer("Manufacturer1");
        monitor1.setSerialNumber("SerialN1");
        monitor1.setPrice(500);
        monitor1.setStockBalance(10);

        monitor2 = new MonitorRequest();
        monitor2.setScreenSize(32);
        monitor2.setManufacturer("Manufacturer2");
        monitor2.setSerialNumber("SerialN2");
        monitor2.setPrice(500);
        monitor2.setStockBalance(20);

        monitorService.addMonitor(monitor1);
        monitorService.addMonitor(monitor2);

    }

    @AfterEach
    public void clean() {
        baseRepository.deleteAll();
        monitorRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/monitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void getById() throws Exception {
        long id = monitorRepository.findAll().get(0).getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/monitor/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(500))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createMonitor() throws Exception {

        MonitorRequest monitor = new MonitorRequest();
        monitor.setScreenSize(27);
        monitor.setManufacturer("Manufacturer");
        monitor.setSerialNumber("SerialN1");
        monitor.setPrice(1500);
        monitor.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/monitor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(monitor))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateMonitor() throws Exception {
        long id = monitorRepository.findAll().get(0).getId();

        MonitorRequest monitor = new MonitorRequest();
        monitor.setScreenSize(24);
        monitor.setManufacturer("Manufacturer");
        monitor.setSerialNumber("SerialN1");
        monitor.setPrice(1500);
        monitor.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/monitor/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(monitor))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.screenSize").value(24))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
