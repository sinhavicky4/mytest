package com.util;

import com.bean.IPAddress;
import com.bean.MyClient;
import com.ProgramDriver;
import com.bean.ServiceImplHeartBeat;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ProcessRefreshTimer extends TimerTask {

    IpGenerator ipList;
    List<MyClient> clientList=new ArrayList<>();;


    public ProcessRefreshTimer(IpGenerator ipList, List<MyClient> clientList) {

        this.ipList = ipList;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        //System.out.println("Refresh ! client "+this.clientList.size() +" Ip list "+this.ipList.getIpAddresses().size());

        ServiceImplHeartBeat serviceImplHeartBeat=new ServiceImplHeartBeat(ipList);
        for (MyClient client : this.clientList) {
            if(client.getAllocatedIp()!=null &&client.getMacAddress()!=null) {

                //this will check for the status of ip address and if this expirty set the status to AVAILABE for pool
                //
                Boolean alive = serviceImplHeartBeat.refresh(client.getMacAddress(), client.getAllocatedIp());
                if (alive) {
                    System.out.println("Time expire for client " +client.getName() );
                    this.ipList.getIpAddresses().forEach(ipAddress -> System.out.println("IP  AVAILABLE FOR POOL"+ipAddress.getIpAddress() +" STATUS " +ipAddress.getStatus() ));
                }else{
                    System.out.println("Process Refresh for checking HeartBeat" );
                }
            }else{
                System.out.println(" No Client having ip from pool :Process Refresh");

                //uncomment below line to assigne ip to client and same process
                //ProgramDriver.startWorking();

            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }
}
