/*
 * 
 * Controller Class for the GUI Applications 
 * 
 */

package application;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.ChatClient;
import application.ChatServer;



public class MainWindowController {

	@FXML private Label lblText;
	@FXML private TextField txtText;
	@FXML private Button btnBtn;
	@FXML private Button new_contact;
	@FXML private TextField name;
	@FXML private TextField IP;
	@FXML private TextField Name_Add;
	@FXML private TextField IP_Add;
	@FXML private Button button_add;
	@FXML private Button Client;
	@FXML private Button Connect;
	

	@FXML private ComboBox<String> existing_contacts;
	@FXML private Button remove_contact;
	@FXML private TextField new_ip;
	@FXML private TextField new_name;
	
	Thread thread;
	ChatServer chatserver;
	ChatClient chatclient;
	
	static int i=0;
	
	
	
	String Name_STR="", IP_STR=""; 
	




	
	

	@FXML
	public void handlebtnNeuesFensterAction(){
		
	}
	
	
	@FXML
	public void Connect_Client(){
		
		try {
			new NewWindow("ChatWindow_Client.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Send_Chat(){
		
		
	}
	@FXML
	public void Import_Contacts(){
		
		Database db= new Database();
		String [][] contacts= db.getContacts();
		db=null;
		ObservableList<String> results = FXCollections.observableArrayList();
		
		
		for (int i=0;i<contacts.length;i++){
			results.add(contacts[i][0]+": "+contacts[i][1]);
			
			
		}
		existing_contacts.setItems(null);
		existing_contacts.setItems(results);
	
		
	}
	
	@FXML
	public void remove_contact(){
		String IP =existing_contacts.getSelectionModel().getSelectedItem().toString();
		IP = IP.substring(IP.indexOf(':')+2, IP.length());
		
		Database db = new Database();
		db.deletecontact(IP);
		Import_Contacts();
		
		
		
		
		
		
		
	}
	
	
	
	

	@FXML 
	public void new_contact() throws IOException {
    Database db=new Database();
    db.setcontact(new_name.getText(), new_ip.getText());
    
    Import_Contacts();
    new_name.clear();new_ip.clear();
		
		//new newWindow("Add_Contact.fxml");
	}

	@FXML
	public void Connect(){
		
	NewWindow window;
	
		try {
			
		window=new NewWindow("ChatWindow_Server.fxml");
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
		
	}

	
	
	@FXML
	public void handleCloseButtonAction(){
		String text_1=(Name_Add.getText());
		String text_2=(IP_Add.getText());
	
		Database db= new Database();
		db.setcontact(text_1, text_2);
		
		
		Stage stage = (Stage)button_add.getScene().getWindow();
		stage.close();
		
		

	}

	
	

	
}
