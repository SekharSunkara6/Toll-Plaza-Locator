package com.example.tollplaza.controller;

import com.example.tollplaza.dto.TollPlazaResponseDto;
import com.example.tollplaza.dto.TollPlazaRequestDto;
import com.example.tollplaza.service.TollPlazaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TollPlazaController.class)
public class TollPlazaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TollPlazaService tollPlazaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenInvalidPincode_thenReturnsBadRequest() throws Exception {
        TollPlazaRequestDto request = new TollPlazaRequestDto();
        request.setSourcePincode("abc123");
        request.setDestinationPincode("560001");

        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid source or destination pincode"));
    }

    @Test
    public void whenSameSourceAndDestination_thenReturnsBadRequest() throws Exception {
        TollPlazaRequestDto request = new TollPlazaRequestDto();
        request.setSourcePincode("560001");
        request.setDestinationPincode("560001");

        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Source and destination pincodes cannot be the same"));
    }

    @Test
    public void whenValidRequest_thenReturnsOk() throws Exception {
        TollPlazaRequestDto request = new TollPlazaRequestDto();
        request.setSourcePincode("110001");
        request.setDestinationPincode("560001");

        // Mock service to return empty list for simplicity
        when(tollPlazaService.getTollPlazasOnRoute(anyString(), anyString()))
                .thenReturn(new TollPlazaResponseDto());

        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

}
