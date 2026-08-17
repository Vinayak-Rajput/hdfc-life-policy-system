package com.hdfclife.strategy;

public class PremiumCalculator {
    PremiumStrategy premiumStrategy;
    double calculatePremium(PremiumStrategy premiumStrategy, double basePremium){
        return (basePremium * premiumStrategy.getPremiumRate())/100;
    }
}
