package com.hdfclife.strategy;

public class UlipPremiumStrategy implements PremiumStrategy{

    int premiumRate = 112;

    @Override
    public double calculate(double basePremium) {

        return (basePremium * premiumRate) / 100;
    }
}
