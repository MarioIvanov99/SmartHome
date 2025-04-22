package com.smart.home.model;

import com.smart.home.util.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DeviceConfig {
    private final String model;
    private final LocalDate productionDate;
    private final BigDecimal powerConsumption;
    private final DeviceType deviceType;

    public DeviceConfig(String model, LocalDate productionDate, BigDecimal powerConsumption, DeviceType deviceType) {
        Validator.checkProductionDate(productionDate);
        Validator.checkNotNegative(powerConsumption, "Power consumption");

        this.model = model;
        this.productionDate = productionDate;
        this.powerConsumption = powerConsumption;
        this.deviceType = deviceType;
    }

    public String getModel() { return model; }
    public LocalDate getProductionDate() { return productionDate; }
    public BigDecimal getPowerConsumption() { return powerConsumption; }
    public DeviceType getDeviceType() { return deviceType; }
}