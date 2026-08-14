package com.hdfclife.config;

public enum AppConfig {
    INSTANCE;

    private final String companyName;
    private final int maxClaimAmount;

    AppConfig(){
        companyName = "HDFC Life";
        maxClaimAmount = 500000;
    }
}
