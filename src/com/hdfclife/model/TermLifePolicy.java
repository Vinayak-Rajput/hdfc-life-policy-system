package com.hdfclife.model;

public class TermLifePolicy extends Policy{
    public TermLifePolicy(String policyNo, String customerName, int premium, PolicyStatus status){
        super(policyNo, customerName, premium, status);
    }

    @Override
    public String getType(){
        return "TERM";
    }
}
