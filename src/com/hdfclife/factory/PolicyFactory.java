package com.hdfclife.factory;

import com.hdfclife.model.*;

public class PolicyFactory {
    public Policy create(String type, String policyNo, String customer, int basePremium, PolicyStatus policyStatus){
        return switch (type) {
            case "TERM" -> new TermLifePolicy(policyNo, customer, basePremium, policyStatus);
            case "ENDOWMENT" -> new EndowmentPolicy(policyNo, customer, basePremium, policyStatus);
            case "ULIP" -> new UlipPolicy(policyNo, customer, basePremium, policyStatus);
            default -> null;
        };
    }
}