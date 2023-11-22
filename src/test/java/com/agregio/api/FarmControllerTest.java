package com.agregio.api;

import com.agregio.api.model.FarmCreationRequest;
import com.agregio.api.model.FarmResponse;
import com.agregio.domain.FarmService;
import com.agregio.domain.model.Farm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Farm controller")
class FarmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private FarmService farmService;


    @Test
    @DisplayName("should accept the creation of the farm and return a farmResponse")
    void shouldAcceptFarmCreationAndReturnFarmResponse() throws Exception {
        FarmCreationRequest farmCreationRequest = new FarmCreationRequest("wind", "ferme éolienne de Beauregard");
        FarmResponse farmResponse = new FarmResponse(1L, "WIND", "ferme éolienne de Beauregard");
        Farm farmToCreate = Farm.toCreate(farmCreationRequest.farmType(), farmCreationRequest.name());
        Farm createdFarm = new Farm(1L, farmToCreate.farmType(), farmToCreate.name());
        when(farmService.create(farmToCreate)).thenReturn(createdFarm);
        mockMvc
                .perform(
                        post("/farms")
                                .content(objectMapper.writeValueAsString(farmCreationRequest))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(farmResponse), true));
        verify(farmService).create(farmToCreate);
        verifyNoMoreInteractions(farmService);


    }
}