package com.example.tollplaza.dto;

import java.util.List;

public class TollPlazaResponseDto {

    private RouteDto route;
    private List<TollPlazaDto> tollPlazas;

    // constructors, getters, setters

    public TollPlazaResponseDto() {
    }

    public TollPlazaResponseDto(RouteDto route, List<TollPlazaDto> tollPlazas) {
        this.route = route;
        this.tollPlazas = tollPlazas;
    }

    public RouteDto getRoute() {
        return route;
    }

    public void setRoute(RouteDto route) {
        this.route = route;
    }

    public List<TollPlazaDto> getTollPlazas() {
        return tollPlazas;
    }

    public void setTollPlazas(List<TollPlazaDto> tollPlazas) {
        this.tollPlazas = tollPlazas;
    }
}
