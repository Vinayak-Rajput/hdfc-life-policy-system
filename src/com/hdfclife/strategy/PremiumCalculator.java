package com.hdfclife.strategy;

public class PremiumCalculator {

    PremiumStrategy premiumStrategy;

    public PremiumCalculator(PremiumStrategy premiumStrategy) {

        this.premiumStrategy = premiumStrategy;
    }

    public void setPremiumStrategy(PremiumStrategy premiumStrategy) {

        this.premiumStrategy = premiumStrategy;
    }

    public double calculatePremium(double basePremium){

        return premiumStrategy.calculate(basePremium);
    }
}
