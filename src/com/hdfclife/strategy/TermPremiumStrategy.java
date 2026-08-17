package com.hdfclife.strategy;

public class TermPremiumStrategy implements PremiumStrategy {

    @Override
    public int getPremiumRate() {
        return 100;
    }
}
