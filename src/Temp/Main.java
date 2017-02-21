

/*
 * 
 * This is the Mail File of this Project
 * 
 * @Author: Boris Lewin
 * 
 * Welcome to the privacy detector
 * 
 */


package Temp;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	 private static Stage primaryStage;
	 public static int starter=0;
	 
	 
	 
	 
	 public static int PortNumber;
	
	 static Stage getStage(){
		 
		 return primaryStage;
	 }
	 
	 public void SetPort(){
		 double i=0;
		 
		 while(i<10000||i>65535){
			 i=Math.random()*65536;
			 
			 
		 }
		 PortNumber =(int)i;
		 
	 }
	 public int getPort(){
		 return PortNumber;
		 
		 
	 }
	 
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		mainWindow();
		SetPort();
	}
	
	
	
	
	public void mainWindow(){
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
			AnchorPane pane = loader.load();
			
			primaryStage.setMinHeight(400.00);
			primaryStage.setMinWidth(500.00);
			
			MainWindowController mainWindowController = loader.getController();
			
			mainWindowController.Import_Contacts();
			
			Scene scene = new Scene(pane);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
