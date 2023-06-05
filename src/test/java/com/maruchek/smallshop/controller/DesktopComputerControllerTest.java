package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DesktopComputerControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DesktopComputerService computerService;

    @Autowired
    DesktopComputerRepository computerRepository;

    @Autowired
    BaseEntityRepository baseRepository;

    private DesktopComputerRequest computer1;
    private DesktopComputerRequest computer2;

    @BeforeEach
    public void setup() {

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
    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders

                        .get("/api/v1/desktop-pc")

                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void getById() throws Exception {

        long id = computerRepository.findAll().get(0).getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/desktop-pc/{id}", id)
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
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
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
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.formFactor").value("DESKTOP"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
