package com.hdfclife.strategy;

public class EndowmentPremiumStrategy implements PremiumStrategy{

    int premiumRate = 108;

    @Override
    public double calculate(double basePremium) {

        return (basePremium * premiumRate) / 100;
    }
}
