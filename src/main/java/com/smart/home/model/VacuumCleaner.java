package com.smart.home.model;

import com.smart.home.util.Validator;

import java.math.BigDecimal;

public class VacuumCleaner {
    private final DeviceConfig config;
    private BigDecimal batteryLevel;
    private boolean hasMop;

    private VacuumCleaner(Builder builder) {
        this.config = builder.config;
        this.batteryLevel = new BigDecimal(100); //%
        this.hasMop = builder.hasMop;
    }

    public BigDecimal getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(BigDecimal batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean getHasMop(){
        return hasMop;
    }

    public void setHasMop(boolean hasMop){
        this.hasMop = hasMop;
    }

    //Task specifies 10% per square foot power drain, but thi makes the power consumption config useless
    //Using power consumption instead of a set value makes the application more robust.
    public void cleanArea(int targetArea) {
        Validator.checkNotNegative(targetArea, "Target area");
        BigDecimal powerConsumption = getHasMop() ? config.getPowerConsumption().multiply(new BigDecimal(2)) : config.getPowerConsumption();
        BigDecimal cleaned = getCleanableArea(targetArea, powerConsumption);

        if(cleaned.equals(new BigDecimal(targetArea))){
            System.out.println("Successfully cleaned " + targetArea + " square meter(s).");
        }
        else {
            System.out.println("Battery insufficient. Only " + cleaned.intValue() + " square meter(s) were cleaned");
        }

        setBatteryLevel(getBatteryLevel().subtract(cleaned.multiply(powerConsumption)));
    }

    private BigDecimal getCleanableArea(int targetArea, BigDecimal powerConsumption) {
        BigDecimal maxCleanableArea = getBatteryLevel().divide(powerConsumption);
        return new BigDecimal(targetArea).min(maxCleanableArea);
    }

    public static class Builder {
        private DeviceConfig config;
        private boolean hasMop;

        public Builder config(DeviceConfig config) {
            this.config = config;
            return this;
        }

        public Builder hasMop(boolean hasMop) {
            this.hasMop = hasMop;
            return this;
        }

        public VacuumCleaner build() {
            return new VacuumCleaner(this);
        }
    }
}