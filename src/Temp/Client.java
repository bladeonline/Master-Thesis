package Temp;

import java.lang.*;
import java.io.*;
import java.net.*;


public class Client implements Runnable{
Socket socket;
public String ip;
public int port;
BufferedReader input;
PrintWriter output;

Socket getSocket(){
	
	return socket;
}

public Client(String ip, int port) throws UnknownHostException, IOException{
	this.port=port;
	this.ip=ip;

	
	
	socket = new Socket(ip, port);
	
	socket.setTcpNoDelay(true);
}


public Client (Socket socket){
	
	this.socket = socket;
	port = socket.getPort();
	//This Constructor is only needed when the client is started from the server Class, this why it is the Localhost as IP
	ip = "localhost";
	
	
	
	
	
}
public void connect(){
	
	if(socket.isConnected()){
	try {
		
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream(), true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}



public String incoming(){

	try {
		
		String response="";
		
		if(input==null)connect();
		
		
		if(input.ready()){
		
		response=input.readLine();
		}
	
		
		if( (response).length()>0){
			
		System.out.println("Eingehende Nachricht: "+response);
		
		return response;
		
		}
		else return "";
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
}

public void outgoing(String text){
	try {
		output.println(text);
		output.flush();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	
}


public void close(){
	if(output!=null)
		output=null;
	if(input!=null)
		input=null;
	if(socket!=null)
		socket=null;
	
	
}

@Override
public void run() {
	// TODO Auto-generated method stub
	

//		connect();

		
	}
}




