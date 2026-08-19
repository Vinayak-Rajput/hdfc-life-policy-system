package com.hdfclife.model;

public abstract class Policy {
    private final String policyNo;
    private final String customerName;
    private final double basePremium;
    private final PolicyStatus status;

    public Policy(String policyNo, String customerName, double basePremium, PolicyStatus status) {
        this.policyNo = policyNo;
        this.customerName = customerName;
        this.basePremium = basePremium;
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getPremium() {
        return basePremium;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %10s | %-8s", policyNo , customerName,basePremium,status);
    }

    public abstract String getType();
}
