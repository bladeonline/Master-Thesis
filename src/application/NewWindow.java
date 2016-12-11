package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewWindow  extends Stage {
    Stage stage;
    public NewWindow(String name) throws IOException{
        stage = this;
        Parent root = FXMLLoader.load(getClass().getResource(name));
       
        
        Scene scene = new Scene(root);
        
        
        
        stage.setScene(scene);
        stage.show();
    }
    }