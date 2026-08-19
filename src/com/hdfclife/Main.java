package com.hdfclife;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.PolicyServiceException;
import com.hdfclife.factory.PolicyFactory;
import com.hdfclife.model.*;
import com.hdfclife.observer.BranchLetterNotifier;
import com.hdfclife.observer.ClaimEventPublisher;
import com.hdfclife.observer.InAppNotifier;
import com.hdfclife.service.ClaimService;
import com.hdfclife.store.PolicyStore;
import com.hdfclife.strategy.PremiumCalculator;
import com.hdfclife.strategy.UlipPremiumStrategy;

import java.util.*;

public class Main {


    static void main() {

        AppConfig appConfig = AppConfig.INSTANCE;

        // Company Name fetched from AppConfig
        String companyName = appConfig.getCompanyName();

        System.out.println("Company: " + companyName);


        // Creation of Policies (for seeding data)
        PolicyStore policyStore = new PolicyStore();

        policyStore.add(PolicyFactory.create("TERM", "HDFC-LIFE-1001", "Anita Sharma", 18500, PolicyStatus.ACTIVE));

        policyStore.add(PolicyFactory.create("ULIP", "HDFC-LIFE-1002", "Rahul Mehta", 42000, PolicyStatus.ACTIVE));

        policyStore.add(PolicyFactory.create("ENDOWMENT", "HDFC-LIFE-1003", "Priya Nair", 27000, PolicyStatus.LAPSED));

        policyStore.add(PolicyFactory.create("TERM", "HDFC-LIFE-1004", "Vikram Singh", 15200, PolicyStatus.ACTIVE));

        policyStore.add(PolicyFactory.create("ULIP", "HDFC-LIFE-1005", "Sneha Patel", 36000, PolicyStatus.ACTIVE));

        policyStore.add(PolicyFactory.create("ENDOWMENT", "HDFC-LIFE-1006", "Anita Sharma", 22000, PolicyStatus.PENDING));

        // Iterating through the policies with help of Iterator
        Iterator<Policy> policyIterator = policyStore.getAll().iterator();

        while(policyIterator.hasNext()){

            System.out.println(policyIterator.next());
        }

        // Printing the unique customer count
        System.out.println("\nUnique customer count: " + policyStore.getUniqueCustomerCount());

        // Checking Exceptional Handling Mechanism for Policy that exist
        try {

            Policy policy = policyStore.getPolicyByNo("HDFC-LIFE-1004");

            System.out.println("\nLookup HDFC-LIFE-1004 -> " + policy.getCustomerName());

        } catch(PolicyServiceException e) {

            System.out.println("Failed Policy Lookup: "  + e.getMessage());

        }

        // Print Sorted Policies using defined TreeMap in Policy Store
        System.out.println("\nTree Map sorted policies:");

        for(String key: policyStore.getSorted().keySet()){

            System.out.println(key);
        }

        // Calculation of Premium for a ULIP Policy
        PremiumCalculator premiumCalculator = new PremiumCalculator(new UlipPremiumStrategy());

        double ulipPremium = premiumCalculator.calculatePremium(policyStore.getPolicyByNo("HDFC-LIFE-1002").getPremium());
        System.out.println("\nULIP Premium for HDFC-LIFE-1002 -> "+ ulipPremium);


        // Registering Observers
        ClaimEventPublisher claimEventPublisher = new ClaimEventPublisher();

        claimEventPublisher.register(new BranchLetterNotifier());
        claimEventPublisher.register(new InAppNotifier());

        // Filing three claims
        List<Claim> claims = Arrays.asList(
                new Claim.ClaimBuilder("HDFC-LIFE-1001",25000, Urgency.HIGH)
                .hospitalName("Apollo")
                .remarks("Hospitalisation")
                .build(),

                new Claim.ClaimBuilder("HDFC-LIFE-1002",18000, Urgency.MEDIUM)
                .hospitalName("Apollo")
                .remarks("Hospitalisation")
                .build(),

                new Claim.ClaimBuilder("HDFC-LIFE-1004",12000, Urgency.LOW)
                .remarks("Just Matured")
                .build()
                );

        // Filing the claims with ClaimService
        ClaimService claimService = new ClaimService(claimEventPublisher);

        for (Claim claim : claims){
            claimService.fileClaim(claim);
        }

        // Approving the claim with urgency HIGH - claims[0] contains the required HIGH urgency claim
        System.out.println("\nUpdating HIGH claim to APPROVED");
        claims.getFirst().updateStatus(ClaimStatus.APPROVED);

        // Notifying the Observers about this
        System.out.println("\nNotifications shown by the Observers regarding update:");
        claimEventPublisher.notifyObservers(claims.getFirst());

        // Building a Priority Queue in order of Claim's Urgency
        System.out.println("\nPriority Queue Poll Order:");
        PriorityQueue<Claim> priorityQueue = policyStore.buildPriorityQueue(claims.get(0), claims.get(1), claims.get(2));

        while(!priorityQueue.isEmpty()){

            System.out.println(priorityQueue.poll().getUrgency());
        }

        // Checking Exceptional Handling Mechanism for Policy that does not exist - HDFC-LIFE-9999
        try {

            policyStore.getPolicyByNo("HDFC-LIFE-9999");

        } catch (PolicyServiceException e) {

            System.out.println("\nFailed Policy Lookup: " + e.getMessage());

        }

        // Checking Exceptional Handling Mechanism for a bad claim
        try {

            Claim badClaim = new Claim.ClaimBuilder("HDFC-LIFE-1008",8000000, Urgency.HIGH).build();
            claimService.fileClaim(badClaim);

        } catch (PolicyServiceException e) {

            System.out.println("\nClaim amount Exceeded: " + e.getMessage());
        }

        // Checking Exceptional Handling Mechanism for creating an invalid policy
        try {

            PolicyFactory.create("INVALID", "HDFC-LIFE-1010", "Vinayak Rajput", 40000,PolicyStatus.ACTIVE);

        } catch(PolicyServiceException e) {

            System.out.println("\n" + e.getMessage());
        }




    }
}
