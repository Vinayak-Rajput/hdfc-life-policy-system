package com.hdfclife.strategy;

public class TermPremiumStrategy implements PremiumStrategy {

    @Override
    public double getPremiumRate() {
        return 1;
    }
}
