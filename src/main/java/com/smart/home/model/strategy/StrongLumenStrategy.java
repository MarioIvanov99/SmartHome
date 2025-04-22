package com.smart.home.model.strategy;

public class StrongLumenStrategy implements LumenStrategy {
    @Override
    public int getLumenOutput(int maxLumens) {
        return maxLumens;
    }
}
