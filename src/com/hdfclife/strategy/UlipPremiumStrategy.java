package com.hdfclife.strategy;

public class UlipPremiumStrategy implements PremiumStrategy{
    @Override
    public double getPremiumRate() {
        return 1.12;
    }
}
