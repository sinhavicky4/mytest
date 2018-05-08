package com.bean;

public class MyClient {
    private int id;
    private String name;
    private String macAddress;
    private String allocatedIp;
    private IPAddress allocatedipAddress;

    public MyClient() {

    }

    public MyClient(int id, String name, String macAddress) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAllocatedIp() {
        return allocatedIp;
    }

    public void setAllocatedIp(String allocatedIp) {
        this.allocatedIp = allocatedIp;
    }

    public IPAddress getAllocatedipAddress() {
        return allocatedipAddress;
    }

    public void setAllocatedipAddress(IPAddress allocatedipAddress) {
        this.allocatedipAddress = allocatedipAddress;
    }
}
