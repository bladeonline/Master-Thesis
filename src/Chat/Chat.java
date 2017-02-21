package Chat;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chat {
	
	ArrayList <Message> message = new ArrayList<Message>();
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("EEE, HH'h':mm'm':ss's'");
	Calendar cal = Calendar.getInstance();
	
	
	
	
void add(){
	
	message.add(new Message("bla", 3, (SimpleDateFormat) sdf.getInstance()));
	
	System.out.println(sdf.format(cal.getTime()));
}





	
	
	
public static void main(String []args){
	

	new Chat().add();
	
}
}
