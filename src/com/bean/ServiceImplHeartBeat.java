package com.bean;

import com.HeartBeatService;
import com.util.IpGenerator;

public class ServiceImplHeartBeat implements HeartBeatService {


    IpGenerator ipList;
    public ServiceImplHeartBeat() {

    }


    public ServiceImplHeartBeat(IpGenerator ipList) {
        this.ipList = ipList;
    }

    @Override
    public Boolean refresh(String macAddress, String allocatedIPAddr) {

        if(allocatedIPAddr!=null && macAddress!=null) {
          /*  IPAddress address = this.ipList.getIpAddresses().stream().
                    filter(ipAddress -> ipAddress.getAssignedClient().getMacAddress().equals(macAddress)).findFirst().get();
          */

            IPAddress  address=ipList.getIpAddresses().stream().
                    filter(ipAddress -> ipAddress.getIpAddress().equals(allocatedIPAddr)).findFirst().get();
            System.out.println("HeartBeat Check");
            System.out.println(" IP " + address.getAssignedClient().getAllocatedIp() +
                    " Allocated Time :" + address.getAllocationTime() +
                    " Allocated Client Name " + address.getAssignedClient().getName() +
                    " Expiry Time " + address.getExpiryTime()
            );


           // System.out.println((address.getAllocationTime().getTime() + address.getExpiryTime()) - (System.currentTimeMillis()));

            if ((address.getAllocationTime().getTime() + address.getExpiryTime()) - (System.currentTimeMillis()) <0) {
                address.getAssignedClient().setAllocatedIp(null);
                address.setStatus("AVAILABLE");
                address.getAssignedClient();


                System.out.println(" ********IP is availabe for allocation in pool "+address.getIpAddress() + " Expired for Client "+address.getAssignedClient().getName() +" *******");
                return true;

            }
        }


        return false;
    }
}
