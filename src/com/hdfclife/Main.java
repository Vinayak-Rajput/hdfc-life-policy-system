package com.hdfclife;

import com.hdfclife.config.AppConfig;

public class Main {
    static void main(String[] args) {
        AppConfig appConfig = AppConfig.INSTANCE;

        // Company Name & Max Claim Amount fetched from AppConfig
        String companyName = appConfig.getCompanyName();
        double maxClaimAccount = appConfig.getMaxClaimAmount();



    }
}
