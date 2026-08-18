package com.hdfclife.observer;

import com.hdfclife.model.Claim;

public class InAppNotifier implements ClaimObserver{
    @Override
    public void onClaimUpdate(Claim claim) {
        System.out.println("In App Notifier's Notification:  \n" + claim.getPolicyNo() +"'s status changed to " + claim.getStatus());
    }
}
