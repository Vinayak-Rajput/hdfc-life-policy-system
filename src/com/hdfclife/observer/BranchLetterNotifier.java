package com.hdfclife.observer;

import com.hdfclife.model.Claim;

public class BranchLetterNotifier implements ClaimObserver {

    @Override
    public void onClaimUpdate(Claim claim) {

        System.out.println("Branch Letter Notifier's Notification: \n" + claim.getPolicyNo() +"'s status changed to " + claim.getStatus());
    }
}
