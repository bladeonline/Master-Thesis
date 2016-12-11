package application;

import application.Main;
import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ConnectionController extends Stage implements Runnable{
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	NewWindow window;
	
	Client client;
	Server server;
	


	
	
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
	
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		stage.close();	
		
	}
	public void run(){

			
	
			 	System.out.println(Main.starter);
			if(Main.starter%2==0)
				server = new Server();
			else
				try {
					client = new Client("localhost", 1000);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			Main.starter++;
			while(true){
			
			}
			
			


				
			
			
			
			
			
			
		}
		
		
	
}
