/*
 * 
 * Controller Class for the GUI Applications 
 * 
 */

package application;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
	@FXML private Button send_chat;
	@FXML private Button close_chat;
	@FXML private Label label_chat;
	@FXML private TextArea text_chat;
	@FXML private ComboBox existing_contacts;
	@FXML private Button remove_contact;
	
	
	
	
	
	
	
	
	
	String Name_STR="", IP_STR=""; 
	

	public Main main;

	public void setMain(Main main){
		this.main = main;
	}


	
	
	@FXML
	public void Connect() throws IOException{
		new newWindow("ChatWindow.fxml");
		
		
	}
	@FXML
	public void handlebtnNeuesFensterAction(){
		
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
    new newWindow("Add_Contact.fxml");
	}

	public class newWindow  extends Stage {
	    Stage stage;
	    public newWindow(String name) throws IOException{
	        stage = this;
	        Parent root = FXMLLoader.load(getClass().getResource(name));
	       
	        
	        Scene scene = new Scene(root);
	        
	        
	        
	        stage.setScene(scene);
	        stage.show();
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
	public void send_chat(){
		
		System.out.println(text_chat.getText());
	if (label_chat.getText().equals(""))
		label_chat.setText(text_chat.getText());	
		else
	label_chat.setText(label_chat.getText()+text_chat.getText()+"\n");
	text_chat.clear();
		
		
		
	}
	
	
	public void close_chat(){
		
		Stage stage = (Stage)close_chat.getScene().getWindow();
		stage.close();	
		
	}
	
}
