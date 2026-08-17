package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.Policy;
import com.hdfclife.model.PolicyStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void main(String[] args) {
        AppConfig appConfig = AppConfig.INSTANCE;

        // Company Name & Max Claim Amount fetched from AppConfig
        String companyName = appConfig.getCompanyName();
        double maxClaimAccount = appConfig.getMaxClaimAmount();

        // Creation of Policies (for seeding data)
        List<Policy> policies = Arrays.asList(
                new PolicyFactory().create("TERM", "HDFC-LIFE-1001", "Anita Sharma", 18500, PolicyStatus.ACTIVE),
                new PolicyFactory().create("ULIP", "HDFC-LIFE-1002", "Rahul Mehta", 42000, PolicyStatus.ACTIVE),
                new PolicyFactory().create("ENDOWMENT", "HDFC-LIFE-1003", "Priya Nair", 27000, PolicyStatus.LAPSED),
                new PolicyFactory().create("TERM", "HDFC-LIFE-1004", "Vikram Singh", 15200, PolicyStatus.ACTIVE),
                new PolicyFactory().create("ULIP", "HDFC-LIFE-1005", "Sneha Patel", 36000, PolicyStatus.ACTIVE),
                new PolicyFactory().create("ENDOWMENT", "HDFC-LIFE-1006", "Anita Sharma", 22000, PolicyStatus.PENDING)
        );

        for(Policy policy : policies){
            System.out.println(policy);
        }






    }
}
