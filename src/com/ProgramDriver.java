package com;

import com.bean.IPAddress;
import com.bean.MyClient;
import com.bean.ServiceImpl;
import com.util.IpGenerator;
import com.util.ProcessRefreshTimer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class ProgramDriver {


    public static void main(String arg[]){
        startWorking();

    }

    public static void startWorking(){
        //generate ip address of the range passed in constructor
        IpGenerator ipList=new IpGenerator(2,10);
        StringBuffer message=new StringBuffer();

       //configure expiry time
        ipList.setExpiryTime(10*1000);
        //printing generate ip addresses
        System.out.println("------------------------*************************------------------");
        ipList.generateList().forEach(ipAddress ->{
            System.out.println(ipAddress.getIpAddress()  +"  "+ipAddress.getExpiryTime());
        });




        System.out.println("------------------------************INITIAL STATUS*************------------------");

        ipList.getIpAddresses().forEach(ipAddress -> {
            System.out.println(ipAddress.getIpAddress() +" "+ipAddress.getStatus());
        });
        System.out.println("------------------------*************************------------------");


        //adding client list
        List<MyClient> clientList=addClient(ipList);
        //assignning IP for client
        clientWorker(ipList,clientList);













    }

    /*
    * Below method assign the ip to client as deallocated ip from client after expiry time
    *
    *
    * */

    public static void clientWorker(IpGenerator ipList,List<MyClient> clientList){
        ServiceImpl service= new ServiceImpl(ipList);
        int clientCouner=0;

        //assigning unique ip to clients
        for(MyClient client:clientList) {
            if(clientCouner<ipList.getIpAddresses().size()) {
                client.setAllocatedIp(service.allocateIp(client));
                clientCouner++;

            }

        }







        System.out.println("------------------------*************IP ALLOCATED************-------------------");

        ipList.getIpAddresses().forEach(ipAddress -> {
            System.out.println(ipAddress.getIpAddress() +" "+ipAddress.getStatus());
        });

        System.out.println("------------------------**********CLIENTS*************** -------------------");

        for(MyClient client:clientList){

            IPAddress address=ipList.getIpAddresses().stream().
                    filter(ipAddress -> ipAddress.getIpAddress().equals(client.getAllocatedIp())).findAny().get();


            System.out.println("Allocating ip to client :" + client.getName() + " MAC :" + client.getMacAddress() +
                    " Ip " + client.getAllocatedIp() +
                    " Allocated Time: "+address.getAllocationTime()  +" Allocated Client Name: "+address.getAssignedClient().getName()
            );
        }
        System.out.println("------------------------*************************-------------------");

        //check ip expiry time
        //timer run for n second to check the heartbeat of IP assigned to cleint

        Timer timer = new Timer();
        timer.schedule(new ProcessRefreshTimer(ipList,clientList), 0, 500);


    }

    //this generate the client list for us

    private static List<MyClient> addClient(IpGenerator ipList){
        MyClient[] clients=new MyClient[5];
        clients[0]=new MyClient(0,"Client 0","00-14-22-01-23-44");
       clients[1]=new MyClient(1,"Client 1","00-14-22-01-23-45");
        clients[2]=new MyClient(2,"Client 2","00-14-22-01-23-46");
        clients[3]=new MyClient(3,"Client 3","00-14-22-01-23-47");
        clients[4]=new MyClient(4,"Client 4","00-14-22-01-23-48");

        List<MyClient> clientList=new ArrayList<>();

        for(MyClient client:clients){
            clientList.add(client);
        }

        return clientList;

    }





}
