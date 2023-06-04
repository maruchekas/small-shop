package com.maruchek.smallshop.controller;

import com.maruchek.smallshop.AbstractTest;
import com.maruchek.smallshop.api.response.LaptopShortResponse;
import com.maruchek.smallshop.service.LaptopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

public class LaptopControllerTest extends AbstractTest {

    @Autowired
    private LaptopService laptopService;

    @Test
    void laptops() throws Exception {

        LaptopShortResponse laptop1 = new LaptopShortResponse()
                .setPrice(100)
                .setManufacturer("manufacturer")
                .setSize(16);

        LaptopShortResponse laptop2 = new LaptopShortResponse()
                .setPrice(100)
                .setManufacturer("manufacturer")
                .setSize(16);

        List<LaptopShortResponse> shortResponseList = List.of(laptop1, laptop2);

        when(laptopService.getAll()).thenReturn(shortResponseList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/laptop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
}
