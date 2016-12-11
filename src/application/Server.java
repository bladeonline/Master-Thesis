package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
private ServerSocket serverSocket;
private Socket socket;
DataInputStream input;
DataOutputStream output;
Boolean NewChat=false;
public Server()     {
	try {
		serverSocket = new ServerSocket(1000);
		
	
		
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}


//public void connection(){
//	try {
//		if(socket.isConnected()){
//			input = new DataInputStream(socket.getInputStream());
//			output = new DataOutputStream(socket.getOutputStream());
//		}
//		
//	
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	
//	
//}

public void run(){
	
	try {
		socket=serverSocket.accept();
		
		clientThread test=new clientThread("localhost", 1000);
	
			
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public String incoming(){
	
	try {
		
		return input.readUTF();
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


}
