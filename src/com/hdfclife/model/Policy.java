package com.hdfclife.model;

public abstract class Policy {
    private String policyNo;
    private String customerName;
    private double premium;
    private PolicyStatus status;

    public Policy(String policyNo, String customerName, double premium, PolicyStatus status) {
        this.policyNo = policyNo;
        this.customerName = customerName;
        this.premium = premium;
        this.status = status;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getPremium() {
        return premium;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %10s | %-8s", policyNo , customerName,premium,status);
    }

    public abstract String getType();
}
