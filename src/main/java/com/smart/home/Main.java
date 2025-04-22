package com.smart.home;

import com.smart.home.model.DeviceConfig;
import com.smart.home.model.DeviceType;
import com.smart.home.model.SmartLight;
import com.smart.home.model.VacuumCleaner;
import com.smart.home.model.strategy.DimLumenStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            DeviceConfig lightConfig = new DeviceConfig(
                    "Philips Hue", LocalDate.of(2023, 5, 10),
                    new BigDecimal("12.5"), DeviceType.MOVABLE
            );

            SmartLight light = new SmartLight.Builder()
                    .config(lightConfig)
                    .maxLumens(120)
                    .build();

            System.out.println(light.getLumenOutput());
            light.setLumenStrategy(new DimLumenStrategy());
            System.out.println(light.getLumenOutput());
        } catch (IllegalArgumentException e) {
            System.err.println("SmartLight error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            DeviceConfig vacuumConfig = new DeviceConfig(
                    "Roomba X", LocalDate.of(2022, 8, 15),
                    new BigDecimal("10"), DeviceType.BUILT_IN
            );

            VacuumCleaner vacuum = new VacuumCleaner.Builder()
                    .config(vacuumConfig)
                    .build();

            vacuum.cleanArea(8);
            vacuum.cleanArea(3);
        } catch (IllegalArgumentException e) {
            System.err.println("VacuumCleaner error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}