package Chat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Edit {
public static void main(String [] args) {
	
    FileReader reader = null;
	try {
		reader = new FileReader("C:\\Testing.csv");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    BufferedReader br = new BufferedReader(reader);
	String input;
	try {
		while((input=br.readLine())!=null){
			
				
			System.out.println(input+";");
			}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	}
}

