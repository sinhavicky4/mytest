package com.bean;

import com.AllocationService;
import com.util.IpGenerator;

import java.util.Date;

public class ServiceImpl implements AllocationService {

 IpGenerator ipList;
 MyClient clinet;
    public ServiceImpl() {

    }

    public ServiceImpl(IpGenerator ipList) {
        this.ipList = ipList;
    }

    @Override
    public String allocate(String macAddress) {

        //allocating ip address to mac



        for (IPAddress address:this.ipList.getIpAddresses())
        {
            if(address.getStatus().equals("AVAILABLE")){
                address.setStatus("ALLOCATED");
                address.setAllocationTime(new Date());
                address.setAssignedClient(this.clinet);
                return address.getIpAddress();

            }

        }
        return null;
    }

    public String  allocateIp(MyClient client){
        this.clinet=client;

       String allocatedIp= allocate(client.getMacAddress());
       if (allocatedIp!=null)
       {return    allocatedIp;}
       else {return "No IP availabe in pool";}







    }
}
