package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
import com.maruchek.smallshop.api.request.LaptopRequest;
import com.maruchek.smallshop.service.LaptopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LaptopControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LaptopService laptopService;

    private LaptopRequest laptop1;
    private LaptopRequest laptop2;

    @BeforeEach
    public void setup() {

        laptop1 = new LaptopRequest();
        laptop1.setSize(16);
        laptop1.setManufacturer("Manufacturer1");
        laptop1.setSerialNumber("SerialN1");
        laptop1.setPrice(1000);
        laptop1.setStockBalance(10);

        laptop2 = new LaptopRequest();
        laptop2.setSize(13);
        laptop2.setManufacturer("Manufacturer2");
        laptop2.setSerialNumber("SerialN2");
        laptop2.setPrice(1000);
        laptop2.setStockBalance(20);

        laptopService.addLaptop(laptop1);
        laptopService.addLaptop(laptop2);

    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/laptop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @Test
    void getById() throws Exception {
        long id = 2;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/laptop/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createLaptop() throws Exception {

        LaptopRequest laptop = new LaptopRequest();
        laptop.setSize(15);
        laptop.setManufacturer("Manufacturer");
        laptop.setSerialNumber("SerialN1");
        laptop.setPrice(1500);
        laptop.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/laptop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(laptop))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateLaptop() throws Exception {
        long id = 1;

        LaptopRequest laptop = new LaptopRequest();
        laptop.setSize(15);
        laptop.setManufacturer("Manufacturer");
        laptop.setSerialNumber("SerialN1");
        laptop.setPrice(1500);
        laptop.setStockBalance(15);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/laptop/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(laptop))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer")
                        .value("Manufacturer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1500))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
