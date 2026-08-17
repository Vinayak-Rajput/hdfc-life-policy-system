package com.hdfclife.strategy;

public class EndowmentPremiumStrategy implements PremiumStrategy{

    @Override
    public double getPremiumRate() {
        return 1.08;
    }
}
