package com.hdfclife.model;

public class Claim implements Comparable<Claim> {
    private String policyNo;
    private double claimAmount;
    private Urgency urgency;
    private String hospitalName;
    private String remarks;
    private ClaimStatus status;

    public String getPolicyNo() {
        return policyNo;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getRemarks() {
        return remarks;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    private Claim(ClaimBuilder claimBuilder ){
        this.policyNo = claimBuilder.policyNo;
        this.claimAmount = claimBuilder.claimAmount;
        this.urgency = claimBuilder.urgency;
        this.status = ClaimStatus.SUBMITTED;
    }

    @Override
    public int compareTo(Claim other) {
        return Integer.compare(this.urgency.ordinal(), other.urgency.ordinal());
    }

    public static class ClaimBuilder{
        private String policyNo;
        private double claimAmount;
        private Urgency urgency;
        private String hospitalName;
        private String remarks;
        private ClaimStatus status;

        public ClaimBuilder(String policyNo, double claimAmount, Urgency urgency){
            this.policyNo = policyNo;
            this.claimAmount = claimAmount;
            this.urgency = urgency;
        }

        public String getPolicyNo() {
            return policyNo;
        }

        public double getClaimAmount() {
            return claimAmount;
        }

        public Urgency getUrgency() {
            return urgency;
        }

        public ClaimStatus getStatus() {
            return status;
        }

        public ClaimBuilder hospitalName(String hospitalName){
            this.hospitalName = hospitalName;
            return this;
        }

        public ClaimBuilder remarks(String remarks){
            this.remarks = remarks;
            return this;
        }

        public Claim build(){
            return new Claim(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Claim Details: \nUrgency: %-8s\nClaim Amount: %-8s\nPolicy Number: %-8s\n",urgency , claimAmount ,policyNo);
    }

    public void updateStatus(ClaimStatus status){
        this.status = status;
    }

}
