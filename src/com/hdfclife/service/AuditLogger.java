package com.hdfclife.service;

import com.hdfclife.exception.PolicyServiceException;

import java.io.FileWriter;
import java.io.IOException;

public class AuditLogger implements AutoCloseable {

    private FileWriter writer;

    public AuditLogger() {

        try {

            writer =  new FileWriter("audit.log", true);

        } catch (IOException e) {

            throw new PolicyServiceException("Failed to open audit log: " + e);
        }
    }

    public void log(String event){

        try {

            writer.write(event + "\n" );

        } catch (IOException e) {

            throw new PolicyServiceException("Failed to Write Audit: "+ e);

        }
    }


    @Override
    public void close() throws Exception {

        try {

            writer.close();

        } catch (IOException e) {

            throw new PolicyServiceException("Failed to Close Audit Log: "+ e);
        }

    }
}
