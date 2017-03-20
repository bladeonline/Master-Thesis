package Chat;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader {
	
	
	
public static void main(String[]bla) throws IOException{
	
	BufferedReader br = new BufferedReader(new FileReader("C:\\Test.csv"));
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	String read="";
//	while((read= br.readLine())!=null)
//		
//	{
//		String test = "";
//		for(int i=0;i<read.length();i++){
//			if(!(read.charAt(i)=='"'))
//				test+=read.charAt(i);
//			
//			
//		}
//		System.out.println(test);
//	}
	
	Console console=System.console();
	Scanner scanner = new Scanner(System.in);
	String line=null;
	String clean=null;
	Character c;
	line=br.readLine();
	for (int i=0;i<1260;i++){
		line = br.readLine();
	}
	for(int i=0;i<250;i++){
	line = br.readLine();
		line = line.substring(line.indexOf(',')+1, line.length()-1);
		clean="";
		for(int j=0;j<line.length();j++){
			c= line.charAt(j);
			if(!(c ==9 || c.isISOControl(c) || c.isIdentifierIgnorable(c)))
				clean+=c;
			
			
			
		}
		clean=clean.toLowerCase();
		if(clean.charAt(clean.length()-1)=='!' || clean.charAt(clean.length()-1)== '.' || clean.charAt(clean.length()-1)== '?')
			clean=clean.substring(0, clean.length()-1);
		System.out.print(clean);
		System.out.print(",");
		
		scanner.next();
		
	}
	
	
}
}

