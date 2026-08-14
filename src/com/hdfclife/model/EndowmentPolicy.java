package com.hdfclife.model;

public class EndowmentPolicy extends Policy{
    public EndowmentPolicy(String policyNo, String customerName, int premium, String status){
        super(policyNo, customerName, premium, status);
    }

    @Override
    public String getType(){
        return "ENDOWMENT";
    }
}
