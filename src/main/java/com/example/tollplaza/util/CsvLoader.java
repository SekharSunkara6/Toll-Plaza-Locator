package com.example.tollplaza.util;

import com.example.tollplaza.model.TollPlaza;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public static List<TollPlaza> loadTollPlazas() {
        List<TollPlaza> plazas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ClassPathResource("toll_plaza_india.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 4)
                    continue;

                double longitude = Double.parseDouble(parts[0].trim());
                double latitude = Double.parseDouble(parts[1].trim());
                String name = parts[2].trim();

                TollPlaza plaza = new TollPlaza(name, latitude, longitude);
                plazas.add(plaza);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plazas;
    }
}
