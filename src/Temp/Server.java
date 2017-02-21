package Temp;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
ServerSocket serverSocket;
Socket socket;
DataInputStream input;
PrintWriter output;


boolean test=true;

Socket GetSocket(){
	return socket;
	
}

ServerSocket GetServerSocket(){
	return serverSocket;
	
}
public Server()     {
	try {
		serverSocket = new ServerSocket(Main.PortNumber);

		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}




public void run(){

	try{

		
		socket=serverSocket.accept();
		connect();
		socket.setTcpNoDelay(true);
		
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

	

		
		
	
	
		
		
 
	

		//clientThread test=new clientThread("localhost", 2000);
	
	
}


public void close(){
	
if(socket!=null){
	input=null;
	output=null;
	socket=null;
	
	
}
	
	serverSocket=null;
	
	
}




public void connect() throws IOException{
	if(socket!=null){
		input= new DataInputStream(socket.getInputStream());
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		
		
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
		
		
		output.println(text);
	
		output.flush();
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	
}


}
