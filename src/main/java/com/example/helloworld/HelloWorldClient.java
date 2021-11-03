package com.example.helloworld;

import java.util.concurrent.TimeUnit;

import javax.jmdns.ServiceInfo;

import com.example.helloworld.GreeterGrpc.GreeterBlockingStub;		//this is from the GreeterGRPC block
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class HelloWorldClient {
	
	public static void main(String [] args) throws Exception{
	
		String host = "localhost";
		int port = 50051;
		
		ServiceInfo serviceInfo1 = JmDNSDiscovery.run("_grpc._tcp.local.");
		System.out.println("service running on port: " + serviceInfo1.getPort());
	
		//setup channel on the host and port above and it is going to communicate on the channel we setup
	
		ManagedChannel channel = ManagedChannelBuilder.
			forAddress(host, port)
			.usePlaintext()
			.build();
	
		//need instance of our stub which has the sayHello method on it which we can call
		GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
	
		//try catch, to try to connect to the server
		//what output proto file has, so pass message from the client to the server, so want to create a request using the HelloRequest object
		try {
			HelloRequest request = HelloRequest.newBuilder().setName(" World").build();		//automatically created in the proto file, to setup the communication
			HelloReply reply = stub.sayHello(request); 	//this will run on the server side - this is the expected reply object
		
			System.out.println(reply.getMessage());
		}
		catch(StatusRuntimeException e) {
			e.printStackTrace();
		}
		finally {
			//shutdown channel
			channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
		}
	
	}
	

}
