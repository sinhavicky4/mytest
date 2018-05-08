package com.util;

import com.bean.IPAddress;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IpGenerator {
    private int StartRange;
    private int EndRange;
    private long expiryTime=1000;
    private Set<IPAddress> ipAddresses;

    public IpGenerator(int startRange, int endRange) {
        StartRange = startRange;
        EndRange = endRange;
        ipAddresses=new HashSet<>();
    }


    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Set<IPAddress> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(Set<IPAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public int getStartRange() {
        return StartRange;
    }

    public void setStartRange(int startRange) {
        StartRange = startRange;
    }

    public int getEndRange() {
        return EndRange;
    }

    public void setEndRange(int endRange) {
        EndRange = endRange;
    }

    public Set<IPAddress> generateList() {
        IPAddress address = null;
        for (int i = this.getStartRange(); i < this.getEndRange(); i++) {
            address = new IPAddress("192.168.0."+ i, expiryTime);
            address.setAllocationTime(new Date());
            this.ipAddresses.add(address);

        }

        return this.ipAddresses;
    }

}
