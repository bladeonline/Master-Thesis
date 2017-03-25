package Chat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Update {

	
	public static void main(String[]a) throws IOException{
		
		
	BufferedReader br = new BufferedReader(new FileReader("C:\\Dictionary_Extended.arff"));
	
	String output;
	while((output=br.readLine())!=null){
		
		output.replaceAll(" 0 ", " null ");
		output.replaceAll(" 1 ", " one ");
		output.replaceAll(" 2 ", " two ");
		output.replaceAll(" 3 ", " three ");
		System.out.println(output);
		
		
		}
	}
	
	
	
}
