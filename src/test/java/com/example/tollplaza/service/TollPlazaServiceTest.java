package com.example.tollplaza.service;

import com.example.tollplaza.dto.TollPlazaResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TollPlazaServiceTest {

    private TollPlazaService tollPlazaService;

    @BeforeEach
    public void setup() {
        tollPlazaService = new TollPlazaService();
    }

    @Test
    public void testValidPincodesReturnTollPlazas() {
        TollPlazaResponseDto response = tollPlazaService.getTollPlazasOnRoute("560064", "411045");

        Assertions.assertNotNull(response);
        Assertions.assertEquals("560064", response.getRoute().getSourcePincode());
        Assertions.assertEquals("411045", response.getRoute().getDestinationPincode());
        Assertions.assertTrue(response.getTollPlazas().size() > 0);
    }

    @Test
    public void testInvalidSourcePincodeThrowsException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tollPlazaService.getTollPlazasOnRoute("999999", "411045");
        });
        Assertions.assertEquals("Invalid source or destination pincode", exception.getMessage());
    }

    @Test
    public void testInvalidDestinationPincodeThrowsException() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tollPlazaService.getTollPlazasOnRoute("560064", "999999");
        });
        Assertions.assertEquals("Invalid source or destination pincode", exception.getMessage());
    }

    @Test
    public void testSameSourceDestinationReturnEmptyOrError() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tollPlazaService.getTollPlazasOnRoute("560064", "560064");
        });
        Assertions.assertEquals("Source and destination pincodes cannot be the same", exception.getMessage());
    }
}
