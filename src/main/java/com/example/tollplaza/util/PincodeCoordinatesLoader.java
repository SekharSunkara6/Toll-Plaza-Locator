package com.example.tollplaza.util;

import com.example.tollplaza.model.Coordinates;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PincodeCoordinatesLoader {

    private static Map<String, Coordinates> pincodeMap = new HashMap<>();

    public static Map<String, Coordinates> loadPincodes() {
        if (!pincodeMap.isEmpty())
            return pincodeMap;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource("indian_pincodes.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 11)
                    continue;

                String pincode = parts[4].trim();
                String latStr = parts[9].trim();
                String lonStr = parts[10].trim();

                if (pincode.isEmpty() || latStr.isEmpty() || lonStr.isEmpty())
                    continue;

                try {
                    double latitude = Double.parseDouble(latStr);
                    double longitude = Double.parseDouble(lonStr);
                    pincodeMap.put(pincode, new Coordinates(latitude, longitude));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pincodeMap;
    }
}
