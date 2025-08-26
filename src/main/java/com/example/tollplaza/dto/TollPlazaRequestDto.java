package com.example.tollplaza.dto;

import jakarta.validation.constraints.NotBlank;

public class TollPlazaRequestDto {

    @NotBlank(message = "Source pincode is required")
    private String sourcePincode;

    @NotBlank(message = "Destination pincode is required")
    private String destinationPincode;

    // getters and setters

    public String getSourcePincode() {
        return sourcePincode;
    }

    public void setSourcePincode(String sourcePincode) {
        this.sourcePincode = sourcePincode;
    }

    public String getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(String destinationPincode) {
        this.destinationPincode = destinationPincode;
    }
}
