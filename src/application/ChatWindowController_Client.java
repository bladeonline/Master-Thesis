package application;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ChatWindowController_Client extends Stage implements Runnable, Initializable{
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	
	
	Client client;
	
	

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
		if(client.incoming().length()>0){
			label_chat.setText(label_chat.getText()+client.incoming()+"\n");
			
			
			
		}
		
		
		
	}
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		stage.close();	
		
	}
	public void run(){

		System.out.println("bla");
		try {
			client = new Client("localhost", 13345);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		if(client!=null)
		System.out.println("DONE!");
			
			while(true){
				
				if(client.getSocket()!=null){
					send();
					receive();
					
				}
			
			}
			
			


				
			
			
			
			
			
			
		}

	
	public void GetIt(){
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	         receive();
	        }
	      });
		
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Client client =new Client("Localhost", 13345);
			Thread thread = new Thread(client);
			thread.start();
			GetIt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
	
}
