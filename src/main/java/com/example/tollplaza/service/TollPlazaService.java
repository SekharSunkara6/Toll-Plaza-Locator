package com.example.tollplaza.service;

import com.example.tollplaza.dto.RouteDto;
import com.example.tollplaza.dto.TollPlazaDto;
import com.example.tollplaza.dto.TollPlazaResponseDto;
import com.example.tollplaza.model.Coordinates;
import com.example.tollplaza.model.TollPlaza;
import com.example.tollplaza.util.CsvLoader;
import com.example.tollplaza.util.GeoUtils;
import com.example.tollplaza.util.PincodeCoordinatesLoader;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TollPlazaService {

    public TollPlazaResponseDto getTollPlazasOnRoute(String sourcePincode, String destinationPincode) {
        // Check for same source and destination
        if (sourcePincode.equals(destinationPincode)) {
            throw new IllegalArgumentException("Source and destination pincodes cannot be the same");
        }

        Map<String, Coordinates> pincodeMap = PincodeCoordinatesLoader.loadPincodes();

        Coordinates sourceCoords = pincodeMap.get(sourcePincode);
        Coordinates destCoords = pincodeMap.get(destinationPincode);

        if (sourceCoords == null || destCoords == null) {
            throw new IllegalArgumentException("Invalid source or destination pincode");
        }

        double routeDistanceKm = GeoUtils.haversineDistance(
                sourceCoords.getLatitude(), sourceCoords.getLongitude(),
                destCoords.getLatitude(), destCoords.getLongitude());

        // Updated tighter adaptive filtering radius calculation
        double minRadius = 3; // Lowered minimum radius 3 km
        double maxRadius = 150; // Lowered maximum radius 150 km
        double bufferFactor = 0.25; // Reduced buffer to 25% of route distance

        double filteringDistanceKm;
        if (routeDistanceKm < minRadius) {
            filteringDistanceKm = routeDistanceKm + 2; // small buffer for very short routes
        } else {
            filteringDistanceKm = routeDistanceKm * bufferFactor;
            filteringDistanceKm = Math.min(filteringDistanceKm, maxRadius);
        }

        List<TollPlaza> allPlazas = CsvLoader.loadTollPlazas();

        List<TollPlazaDto> filteredTolls = new ArrayList<>();
        Set<String> addedPlazaKeys = new HashSet<>();

        for (TollPlaza plaza : allPlazas) {
            double distFromSource = GeoUtils.haversineDistance(
                    sourceCoords.getLatitude(), sourceCoords.getLongitude(),
                    plaza.getLatitude(), plaza.getLongitude());

            if (distFromSource <= filteringDistanceKm) {
                String normalizedName = plaza.getName().toLowerCase().trim().replaceAll("[^a-z0-9]", "");
                String key = normalizedName + "_" + plaza.getLatitude() + "_" + plaza.getLongitude();

                if (!addedPlazaKeys.contains(key)) {
                    filteredTolls.add(new TollPlazaDto(plaza.getName(), plaza.getLatitude(), plaza.getLongitude(),
                            distFromSource));
                    addedPlazaKeys.add(key);
                }
            }
        }

        TollPlazaResponseDto response = new TollPlazaResponseDto();
        response.setRoute(new RouteDto(sourcePincode, destinationPincode, routeDistanceKm));
        response.setTollPlazas(filteredTolls);

        return response;
    }
}
