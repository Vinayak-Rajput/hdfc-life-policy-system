package com.hdfclife.factory;

import com.hdfclife.model.*;

public class PolicyFactory {
    Policy create(String type, String policyNo, String customer, int basePremium, ClaimStatus claimStatus){
        return switch (type) {
            case "TERM" -> new TermLifePolicy(policyNo, customer, basePremium, claimStatus);
            case "ENDOWMENT" -> new EndowmentPolicy(policyNo, customer, basePremium, claimStatus);
            case "ULIP" -> new UlipPolicy(policyNo, customer, basePremium, claimStatus);
            default -> null;
        };
    }
}