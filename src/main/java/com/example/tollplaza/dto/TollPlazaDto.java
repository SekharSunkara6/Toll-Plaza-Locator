package com.example.tollplaza.dto;

public class TollPlazaDto {

    private String name;
    private double latitude;
    private double longitude;
    private double distanceFromSource; // in KM

    // constructors, getters, setters

    public TollPlazaDto() {
    }

    public TollPlazaDto(String name, double latitude, double longitude, double distanceFromSource) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFromSource = distanceFromSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }
}
