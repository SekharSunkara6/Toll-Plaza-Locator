package com.example.tollplaza.dto;

public class RouteDto {

    private String sourcePincode;
    private String destinationPincode;
    private double distanceInKm;

    // constructors, getters, setters

    public RouteDto() {
    }

    public RouteDto(String sourcePincode, String destinationPincode, double distanceInKm) {
        this.sourcePincode = sourcePincode;
        this.destinationPincode = destinationPincode;
        this.distanceInKm = distanceInKm;
    }

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

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }
}
