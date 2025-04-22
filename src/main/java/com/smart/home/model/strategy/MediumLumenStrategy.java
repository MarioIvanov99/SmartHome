package com.smart.home.model.strategy;

public class MediumLumenStrategy implements LumenStrategy {
    @Override
    public int getLumenOutput(int maxLumens) {
        return (int) (maxLumens * 0.75);
    }
}
