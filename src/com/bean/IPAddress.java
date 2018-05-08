package com.bean;

import java.util.Date;

public class IPAddress {

    private String ipAddress;
    private long expiryTime;
    private String status="AVAILABLE";
    private Date allocationTime;
    private MyClient assignedClient;


    public IPAddress() {

    }


    public IPAddress(String ipAddress, long expiryTime) {
        this.ipAddress = ipAddress;
        this.expiryTime = expiryTime;

    }



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(Date allocationTime) {
        this.allocationTime = allocationTime;
    }

    public MyClient getAssignedClient() {
        return assignedClient;
    }

    public void setAssignedClient(MyClient assignedClient) {
        this.assignedClient = assignedClient;
    }
}
