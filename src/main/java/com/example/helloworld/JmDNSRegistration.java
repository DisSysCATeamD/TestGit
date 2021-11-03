package com.example.helloworld;

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class JmDNSRegistration {
    public void run(String serviceType, String serviceName, int port, String description) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = ServiceInfo.create(serviceType, serviceName, port, description);
            jmdns.registerService(serviceInfo);
            System.out.println("jmdns started");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}