package com.hdfclife.store;

import com.hdfclife.exception.PolicyNotFoundException;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Policy;

import java.util.*;

public class PolicyStore {

    private final ArrayList<Policy> policies = new ArrayList<>();

    private final HashMap<String, Policy> map = new HashMap<>();

    private final TreeMap<String,Policy> sorted = new TreeMap<>();

    private final HashSet<String> customers = new HashSet<>();

    public void add(Policy policy){

        policies.add(policy);

        map.put(policy.getPolicyNo(),policy);

        sorted.put(policy.getPolicyNo(), policy);

        customers.add(policy.getCustomerName());
    }

    public ArrayList<Policy> getAll() {

        return policies;
    }

    public int getUniqueCustomerCount() {

        return customers.size();
    }

    public Policy getPolicyByNo(String policyNo){

        if(map.containsKey(policyNo)){

            return map.get(policyNo);
        }else{

            throw new PolicyNotFoundException("Policy Not Found!");
        }
    }

    public TreeMap<String, Policy> getSorted(){

        return sorted;
    }

    public PriorityQueue<Claim> buildPriorityQueue(Claim... claims){

        PriorityQueue<Claim> queue = new PriorityQueue<>();

        queue.addAll(List.of(claims));

        return queue;
    }
}
