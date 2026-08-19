package com.hdfclife.observer;

import com.hdfclife.model.Claim;
import com.hdfclife.model.ClaimStatus;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ClaimEventPublisher {

    private final List<ClaimObserver> observers = new ArrayList<>();

    public void register(ClaimObserver observer){

        observers.add(observer);
    }

    public void deregister(ClaimObserver observer){

        observers.remove(observer);
    }

    public void notifyObservers(Claim claim){

        for(ClaimObserver observer: observers){

            observer.onClaimUpdate(claim);
        }
    }

    public void updateStatus(Claim claim, ClaimStatus newClaimStatus){

        claim.updateStatus(newClaimStatus);

        System.out.println("\nClaim Update:\n");

        notifyObservers(claim);
    }
}
