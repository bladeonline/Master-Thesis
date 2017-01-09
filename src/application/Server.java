package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
ServerSocket serverSocket;
Socket socket;
DataInputStream input;
DataOutputStream output;
Boolean NewChat=false;

Socket GetSocket(){
	return socket;
	
}

ServerSocket GetServerSocket(){
	return serverSocket;
	
}
public Server()     {
	try {
		serverSocket = new ServerSocket(13345);
		
	
		
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}




public void run(){
	System.out.println("test");
	while(true){
	try {
		socket=serverSocket.accept();
		
	
		
	
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	if (socket!=null){
		System.out.println("geht was");
		
		System.out.println(socket.getPort());
		}
		//clientThread test=new clientThread("localhost", 2000);
		if(socket!=null)
			try {
				connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


public void connect() throws IOException{
	if(socket!=null){
		input= new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		
		
	}
	
}

public String incoming(){
	
	try {
		if(socket!=null){
		if(input.readUTF().length()>0)
		
		return input.readUTF();
		else return "";
		}
		else return "";
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
}

public void outgoing(String text){
	try {
		if(socket!=null) System.out.println("geht doch");
		
		output.writeUTF(text);
		output.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	
}


}
