package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
<<<<<<< HEAD
import com.maruchek.smallshop.api.request.DesktopComputerRequest;
import com.maruchek.smallshop.enums.FormFactor;
import com.maruchek.smallshop.repository.BaseEntityRepository;
import com.maruchek.smallshop.repository.DesktopComputerRepository;
import com.maruchek.smallshop.service.DesktopComputerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
=======
import com.maruchek.smallshop.api.request.MonitorRequest;
import com.maruchek.smallshop.service.MonitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

<<<<<<< HEAD
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DesktopComputerControllerTest extends AbstractTest {
=======
public class MonitorControllerTest extends AbstractTest {
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5

    @Autowired
    private MockMvc mockMvc;

    @Autowired
<<<<<<< HEAD
    private DesktopComputerService computerService;

    @Autowired
    DesktopComputerRepository computerRepository;

    @Autowired
    BaseEntityRepository baseRepository;

    private DesktopComputerRequest computer1;
    private DesktopComputerRequest computer2;
=======
    private MonitorService monitorService;

    private MonitorRequest monitor1;
    private MonitorRequest monitor2;
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5

    @BeforeEach
    public void setup() {

<<<<<<< HEAD
        computer1 = new DesktopComputerRequest();
        computer1.setFormFactor(FormFactor.DESKTOP.getValue());
        computer1.setManufacturer("Manufacturer1");
        computer1.setSerialNumber("SerialN1");
        computer1.setPrice(500);
        computer1.setStockBalance(10);

        computer2 = new DesktopComputerRequest();
        computer2.setFormFactor(FormFactor.MONO_BLOCK.getValue());
        computer2.setManufacturer("Manufacturer2");
        computer2.setSerialNumber("SerialN2");
        computer2.setPrice(500);
        computer2.setStockBalance(20);

        computerService.addComputer(computer1);
        computerService.addComputer(computer2);

    }

    @AfterEach
    public void clean() {
        baseRepository.deleteAll();
        computerRepository.deleteAll();
=======
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

>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
<<<<<<< HEAD
                        .get("/api/v1/desktop-pc")
=======
                        .get("/api/v1/monitor")
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void getById() throws Exception {
<<<<<<< HEAD
        long id = computerRepository.findAll().get(0).getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/desktop-pc/{id}", id)
=======
        long id = 2;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/monitor/{id}", id)
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
<<<<<<< HEAD
                        .value("Manufacturer1"))
=======
                        .value("Manufacturer2"))
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(500))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
<<<<<<< HEAD
    void createDesktopComputer() throws Exception {

        DesktopComputerRequest computer = new DesktopComputerRequest();
        computer.setFormFactor(FormFactor.DESKTOP.getValue());
        computer.setManufacturer("Manufacturer");
        computer.setSerialNumber("SerialN1");
        computer.setPrice(1500);
        computer.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/desktop-pc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(computer))
=======
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
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
<<<<<<< HEAD
    void updateDesktopComputer() throws Exception {
        long id = computerRepository.findAll().get(0).getId();

        DesktopComputerRequest computer = new DesktopComputerRequest();
        computer.setFormFactor(FormFactor.DESKTOP.getValue());
        computer.setManufacturer("Manufacturer");
        computer.setSerialNumber("SerialN1");
        computer.setPrice(1500);
        computer.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/desktop-pc/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(computer))
=======
    void updateMonitor() throws Exception {
        long id = 1;

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
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1500))
<<<<<<< HEAD
                .andExpect(MockMvcResultMatchers.jsonPath("$.formFactor").value("DESKTOP"))
=======
                .andExpect(MockMvcResultMatchers.jsonPath("$.screenSize").value(24))
>>>>>>> 3a7e933889ff3a2db8fc97032034dfeb09d5edb5
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
