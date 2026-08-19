package com.hdfclife.strategy;

public class TermPremiumStrategy implements PremiumStrategy {

    int premiumRate = 100;

    @Override
    public double calculate(double basePremium) {

        return (basePremium * premiumRate) / 100;
    }
}
