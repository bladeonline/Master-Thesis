package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ChatWindowController_Server extends Stage implements Runnable, Initializable{
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	
	
	
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
	
	public void send(){
		
		if (text_chat.getText().length()>0){
			server.outgoing(text_chat.getText());
			text_chat.clear();
			
		}
		
		
	}
	
	public void receive(){
		if(server.incoming().length()>0&&!server.incoming().equals("")){
			label_chat.setText(label_chat.getText()+server.incoming()+"\n");
			
			
			
		}
		
		
		
	}
	
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		stage.close();	
		
	}
	public void run(){

		System.out.println("test");
		server = new Server();
		
		
				
			
			
			
			while(true){
				
				if(server.GetSocket()!=null){
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
	
		Server server=new Server();
		Thread thread = new Thread(server);
		thread.start();
		GetIt();
		
		
	}
			
			


				
			
			
			
			
			
			
		}
		
		
	

