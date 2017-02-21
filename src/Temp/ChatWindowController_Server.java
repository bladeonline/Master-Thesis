package Temp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChatWindowController_Server extends Stage implements Runnable, Initializable{
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	@FXML private Button CloseButton;
	
	
	Server server;
	Thread thread;


	
	
@FXML
public void exitApplication(ActionEvent event){
	
	Platform.exit();

	
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
			
			
			
			server.outgoing(text_chat.getText());
			label_chat.setText(label_chat.getText()+text_chat.getText()+"\n");
			text_chat.clear();
			
		}
		

		
	}
	
	public void receive(){
		if(server!=null){
		if(server.socket!=null){
		if(server.incoming().length()>0){
			label_chat.setText(label_chat.getText()+server.incoming()+"\n");
			
				}
			
			}
		
		}
		
	}
	
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		
		stage.close();	
		
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
	
		
		server=new Server();
	
		thread = new Thread(server);
	
		thread.start();

		
		//GetIt();
		
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
			
			


				
			
			
			
			
			
			
		}
		
		
	

