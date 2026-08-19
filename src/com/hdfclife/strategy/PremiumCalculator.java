package com.hdfclife.strategy;

public class PremiumCalculator {

    PremiumStrategy premiumStrategy;

    public PremiumCalculator(PremiumStrategy premiumStrategy) {

        this.premiumStrategy = premiumStrategy;
    }

    public void setPremiumStrategy(PremiumStrategy premiumStrategy) {

        this.premiumStrategy = premiumStrategy;
    }

    double calculatePremium(PremiumStrategy premiumStrategy, double basePremium){

        return premiumStrategy.calculate(basePremium);
    }
}
