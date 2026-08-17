package com.hdfclife.model;

public class UlipPolicy extends Policy {
    public UlipPolicy(String policyNo, String customerName, int premium, PolicyStatus status){
        super(policyNo, customerName, premium, status);
    }

    @Override
    public String getType(){
        return "ULIP";
    }
}
