package com.hdfclife.model;

public class Claim implements Comparable<Claim> {
    private final String policyNo;
    private final double claimAmount;
    private final Urgency urgency;
    private final String hospitalName;
    private final String remarks;
    private ClaimStatus status = ClaimStatus.SUBMITTED;

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
        this.hospitalName = claimBuilder.hospitalName;
        this.remarks = claimBuilder.remarks;
    }

    @Override
    public int compareTo(Claim other) {
        return this.urgency.ordinal() - other.urgency.ordinal();
    }

    public static class ClaimBuilder{
        private final String policyNo;
        private final double claimAmount;
        private final Urgency urgency;
        private String hospitalName;
        private String remarks;

        public ClaimBuilder(String policyNo, double claimAmount, Urgency urgency){
            this.policyNo = policyNo;
            this.claimAmount = claimAmount;
            this.urgency = urgency;
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
