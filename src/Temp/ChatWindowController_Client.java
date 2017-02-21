package Temp;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;

import Temp.Main;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ChatWindowController_Client extends Stage implements Initializable{
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	
	
	Client client;
	Thread thread;
	Thread incoming;
	int i;
	public MessageHandler handler;
	String content="";
	

public ChatWindowController_Client() throws UnknownHostException, IOException{


	
}
	
	
	@FXML
		public void send_chat(){
		
		System.out.println(text_chat.getText());
	if (label_chat.getText().equals(""))
		label_chat.setText(text_chat.getText());	
		else{
	label_chat.setText(label_chat.getText()+text_chat.getText()+"\n");
	text_chat.clear();
		
		}
		
	}
	
	public void send(){
		
		if (text_chat.getText().length()>0){
			client.outgoing(text_chat.getText());
			text_chat.clear();
			
		}
		
		
	}
	
	public void receive(){
		if(client!=null){
		if(client.socket!=null){
			String content = client.incoming();
		if(content.length()>0){
		
			label_chat.setText(label_chat.getText()+content+"\n");
			
			
			
				}
		
			}
		}
		
	}
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		stage.close();	
		
	}


	
	public Runnable GetIt(){
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	         
	    		if(client!=null){
	    			if(client.socket!=null){
	    				String content = client.incoming();
	    			if(content.length()>0){
	    			
	    				label_chat.setText(label_chat.getText()+content+"\n");
	    				
	    				
	    				
	    					}
	    			
	    				}
	    			}
	         
	        }
	      });
		return client;
		
		
		
	}
	
	
	
	public void receive_test1(){
		
		
	

		
		

		        Platform.runLater(new Runnable(){

		        	
		           public void run(){
		        	   
		        
		        	   
		        	String content = "";
					
					content = client.incoming();
					
					if(content.length()>0){
					
					label_chat.setText(label_chat.getText()+content+"\n");
					}
					}
		           

		        });
		        
		 		
		    }
		
		
		
		

	
	
		@FXML
		public void test() {
			Task<Void> task= new Task<Void>(){

				@Override
				protected Void call() throws Exception {
					
						while(true){
		        	content = "";
					
					content = client.incoming();
					
					if(content.length()>0){
					
					set_message();
					}
					}
				}
				
				
				
			};
			
		incoming = new Thread(task);
		incoming.setDaemon(true);
		incoming.start();
		
			
		}
	
	
	public void receive_test(){
	
		new Thread(new Runnable() {
		    @Override public void run() {
		    
		       
		        Platform.runLater(new Runnable() {
		            @Override public void run() {
		            	
		            	
			        	String content = "";
						
						content = client.incoming();
						
						if(content.length()>0){
						
						set_message();
						
						}
		                
		            }
		        
		        });
		    }
		    
		}).start();
		
		
	}
	
	@FXML
	public void set_message(){
		label_chat.setText(label_chat.getText()+content+"\n");
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			client =new Client("Localhost", Main.PortNumber);
			thread = new Thread(client);
			
			thread.start();
		
		
	
		
			for(int i=0;i<1000;i++);
			
			
			
			
//			Incoming_Message test = new Incoming_Message(client, label_chat, text_chat);
//					
//			incoming = new Thread(test);
//			incoming.start();
//			
//			receive_test();
			
				test();
			
			
			

			    
			    
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			





	}
		
	
}
