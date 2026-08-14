package com.hdfclife.model;

public abstract class Policy {
    private String policyNo;
    private String customerName;
    private double premium;
    private String status;

    public Policy(String policyNo, String customerName, double premium, String status) {
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

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return policyNo + '|' + customerName + '|'
                + premium + "|" + status;
    }

    public abstract String getType();
}
