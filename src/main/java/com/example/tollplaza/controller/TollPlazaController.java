package com.example.tollplaza.controller;

import com.example.tollplaza.dto.TollPlazaRequestDto;
import com.example.tollplaza.dto.TollPlazaResponseDto;
import com.example.tollplaza.service.TollPlazaService;
import com.example.tollplaza.util.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/toll-plazas")
public class TollPlazaController {

    private final TollPlazaService tollPlazaService;

    public TollPlazaController(TollPlazaService tollPlazaService) {
        this.tollPlazaService = tollPlazaService;
    }

    @PostMapping
    public ResponseEntity<?> getTollPlazas(@Valid @RequestBody TollPlazaRequestDto requestDto) {
        String src = requestDto.getSourcePincode();
        String dest = requestDto.getDestinationPincode();

        if (!ValidationUtils.isValidPincode(src) || !ValidationUtils.isValidPincode(dest)) {
            throw new IllegalArgumentException("Invalid source or destination pincode");
        }

        if (src.equals(dest)) {
            throw new IllegalArgumentException("Source and destination pincodes cannot be the same");
        }

        TollPlazaResponseDto responseDto = tollPlazaService.getTollPlazasOnRoute(src, dest);
        return ResponseEntity.ok(responseDto);
    }
}
