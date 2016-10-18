/*
 * 
 * The ChatServer 
 * 
 */

package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class ChatServer{
	
	public static int port=10000;
	ServerSocket socket = null;
	String message;
	DataInputStream is;
	PrintStream os;
	Socket clientsocket;
	
	public void open_server()
			{	
	try{
		
		socket=new ServerSocket(port);
		port++;
	}catch(IOException e){
		e.printStackTrace();
	}
	
	
			
			
			
			
			
			try{
				
				clientsocket = socket.accept();
				is= new DataInputStream(clientsocket.getInputStream());
				os= new PrintStream(clientsocket.getOutputStream());
				
				
				
				
			}catch(IOException e1){
				e1.printStackTrace();
			}
		
		
		
	}

	
}