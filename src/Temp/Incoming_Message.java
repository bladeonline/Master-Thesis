package Temp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;





public class Incoming_Message implements Runnable{
	
	Server server;
	Client client;
	Label label;
	TextArea text;



	public Incoming_Message(Client client, Label label, TextArea text){
		
		this.client=client;
		this.label=label;
		this.text=text;
		
	}
	
	public Incoming_Message(Server server, Label label, TextArea text){
		
		this.server=server;
		this.label=label;
		this.text=text;
	
	}
	
@FXML
public void incoming_Client(){
	if(client!=null){
		if(client.socket!=null){
			String incoming = client.incoming();
			if(incoming.length()>0){
				System.out.println("Irgend ein text ist doch schon da");
				label.setText(label.getText()+incoming+"\n");
			
			}
		
		}
	
	
	
	}
	
}
@FXML
public void Incoming_Server(){
	if(server!=null){
	if(server.socket!=null){
	if(server.incoming().length()>0){
		label.setText(label.getText()+client.incoming()+"\n");
		
			}
		
		}
	
	}
	
}

@Override
@FXML
public void run() {
	
	if(client.input==null)
		client.connect();
	
	while(true){
	if (client !=null)
		incoming_Client();
	else
		Incoming_Server();	
	
			}
		}			
	
}
