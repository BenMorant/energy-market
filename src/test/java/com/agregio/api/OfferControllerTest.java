package com.agregio.api;

import com.agregio.api.model.HourlyBlockCreationRequest;
import com.agregio.api.model.HourlyBlockResponse;
import com.agregio.api.model.OfferCreationRequest;
import com.agregio.api.model.OfferResponse;
import com.agregio.domain.OfferService;
import com.agregio.domain.model.HourlyBlock;
import com.agregio.domain.model.MarketType;
import com.agregio.domain.model.Offer;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Offer controller")
class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private OfferService offerService;


    @Test
    @DisplayName("should accept the creation of the offer and return a offerResponse")
    void shouldAcceptOfferCreationAndReturnOfferResponse() throws Exception {
        HourlyBlockCreationRequest hourlyBlockCreationRequest = new HourlyBlockCreationRequest(1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);
        OfferCreationRequest offerCreationRequest = new OfferCreationRequest("primary", "offer1", List.of(hourlyBlockCreationRequest));
        HourlyBlockResponse hourlyBlockResponse = new HourlyBlockResponse(1L, 1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);
        OfferResponse offerResponse = new OfferResponse(1L, "PRIMARY", "offer1", List.of(hourlyBlockResponse));
        HourlyBlock hourlyBlock = new HourlyBlock(1L, 1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);
        Offer offer = new Offer(1L, MarketType.PRIMARY, "offer1", List.of(hourlyBlock));
        when(offerService.create(any())).thenReturn(offer);
        mockMvc
                .perform(
                        post("/offers")
                                .content(objectMapper.writeValueAsString(offerCreationRequest))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(offerResponse), true));
        verify(offerService).create(any());
        verifyNoMoreInteractions(offerService);


    }

    @Test
    @DisplayName("should return offers for primary market")
    void shouldReturnOfferResponsesForAMarket() throws Exception {
        HourlyBlockResponse hourlyBlockResponse = new HourlyBlockResponse(1L, 1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);
        OfferResponse offerResponse = new OfferResponse(1L, "PRIMARY", "offer1", List.of(hourlyBlockResponse));
        HourlyBlock hourlyBlock = new HourlyBlock(1L, 1L, BigDecimal.TEN, BigDecimal.TWO, LocalDate.now(), 4);

        String market = "primary";
        Offer offer = new Offer(1L, MarketType.PRIMARY, "offer1", List.of(hourlyBlock));
        when(offerService.getByMarket(any())).thenReturn(List.of(offer));
        mockMvc
                .perform(
                        get("/offers").queryParam("market", market)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(offerResponse)), true));
        verify(offerService).getByMarket(MarketType.PRIMARY);
        verifyNoMoreInteractions(offerService);


    }
}