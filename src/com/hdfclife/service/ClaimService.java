package com.hdfclife.service;

import com.hdfclife.config.AppConfig;
import com.hdfclife.exception.InvalidClaimException;
import com.hdfclife.exception.PolicyServiceException;
import com.hdfclife.model.Claim;
import com.hdfclife.observer.ClaimEventPublisher;

public class ClaimService {

    private final ClaimEventPublisher claimEventPublisher;

    public ClaimService(ClaimEventPublisher claimEventPublisher) {

        this.claimEventPublisher = claimEventPublisher;
    }

    public void fileClaim(Claim claim) {

        if(claim.getClaimAmount() <= 0 || claim.getClaimAmount() > AppConfig.INSTANCE.getMaxClaimAmount()) {

            throw new InvalidClaimException("Invalid Claim Amount: "+ claim.getClaimAmount());
        }

        try (AuditLogger auditLogger = new AuditLogger()) {

            auditLogger.log("Filed Claim for Policy: " + claim.getPolicyNo());

        } catch (Exception e){

            throw new PolicyServiceException("Audit Log Failure: " + e);
        }

    }
}
