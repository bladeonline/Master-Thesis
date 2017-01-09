package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;


public class Client implements Runnable{
Socket socket;
private String ip;
private int port;
DataInputStream input;
DataOutputStream output;

Socket getSocket(){
	
	return socket;
}

public Client(String ip, int port) throws UnknownHostException, IOException{
	this.port=port;
	this.ip=ip;

	
	
	socket = new Socket(ip, port);

}
public void connect(){
	
	if(socket.isConnected()){
	try {
		
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}


public String incoming(){
	
	try {
		if(input.readUTF().length()>0)
		
		return input.readUTF();
		else return "";
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
}

public void outgoing(String text){
	try {
		output.writeUTF(text);
		output.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	
}

public void establish_connection(){
	
	
	
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
	
	if(socket!=null){
		connect();

		}
	}
}




