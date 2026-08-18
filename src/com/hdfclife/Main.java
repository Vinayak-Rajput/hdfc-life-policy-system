package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.*;
import com.hdfclife.observer.BranchLetterNotifier;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.InAppNotifier;

import java.util.Arrays;
import java.util.Iterator;
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

        // Iterating through the policies with help of Iterator
        Iterator<Policy> policyIterator = new Iterator<Policy>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < policies.size();
            }

            @Override
            public Policy next() {
                return policies.get(index++);
            }
        };

        while(policyIterator.hasNext()){
            System.out.println(policyIterator.next());
        }

        // Registering Observers
        ClaimEventPublisher claimEventPublisher = new ClaimEventPublisher();

        claimEventPublisher.subscribe(new BranchLetterNotifier());
        claimEventPublisher.subscribe(new InAppNotifier());

        // Filing three claims
        Claim claim1  = new Claim.ClaimBuilder("HDFC-LIFE-1001",30000, Urgency.HIGH)
                .hospitalName("Apollo Hospital")
                .remarks("Hospitalisation")
                .build();

        Claim claim2 = new Claim.ClaimBuilder("HDFC-LIFE-1002",30000, Urgency.MEDIUM)
                .hospitalName("AIMS Delhi")
                .remarks("Minor Fractures; Discharges Last Week")
                .build();

        Claim claim3 = new Claim.ClaimBuilder("HDFC-LIFE-1004",30000, Urgency.LOW)
                .remarks("Just Matured")
                .build();

        // Approving the claim with urgency HIGH
        claimEventPublisher.updateStatus(claim1, ClaimStatus.APPROVED);

    }
}
