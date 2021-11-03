package com.example.helloworld;

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;



public class JmDNSDiscovery {
	
	public static ServiceInfo run(String serviceType) {
		ServiceInfo serviceInfo = null;
		
		try {
			MyServiceListener listener = new MyServiceListener();
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());	//create a service listener which is going to listen to services in the background
			jmdns.addServiceListener(serviceType, listener);  //need to pass in an instance of the services listener
			serviceInfo = listener.getServiceInfo();
			
			System.out.println("Client Started");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serviceInfo;
	}
	
	public static class MyServiceListener implements ServiceListener{
		
		int port;
		ServiceInfo serviceInfo;

		public void serviceAdded(ServiceEvent event) {
			// TODO Auto-generated method stub
			ServiceInfo serviceInfo = event.getInfo();	//going to tell us what the jmDMS port is running on 
			this.port = serviceInfo.getPort();
			this.serviceInfo = serviceInfo;
			
			System.out.println("running on port: "+serviceInfo.getPort());
			System.out.println("Service added" + event.getInfo());
			
		}

		public void serviceRemoved(ServiceEvent event) {
			// TODO Auto-generated method stub
			System.out.println("Service removed" + event.getInfo());
			
		}

		public void serviceResolved(ServiceEvent event) {
			// TODO Auto-generated method stub
			ServiceInfo serviceInfo = event.getInfo();
			this.port = serviceInfo.getPort();
			this.serviceInfo = serviceInfo;
			
			System.out.println("running on port: "+serviceInfo.getPort());
			System.out.println("Service resolved" + event.getInfo());
			
		}
		
		public int getPort() {
			return port;
			
		}
		
		public ServiceInfo getServiceInfo() {
			return serviceInfo;
		}
		
	}

}
