package Chat;

import java.util.ArrayList;

public class Message {
	String message;
	int level;
	String time;
	ArrayList <Expression> found_expressions;
	

	public Message(String message, int level, String time) {
		this.message = message;
		this.level = level;
		this.time=time;

	}
	
	public Message(String message, int level, String time, ArrayList<Expression> found_expression) {
		this.message = message;
		this.level = level;
		this.time=time;
		this.found_expressions=found_expression;

	}

	private String getMessage() {
		return message;
	}
	
	private ArrayList<Expression> getFound_expressions(){
		return found_expressions;
		
	}

	private void setMessage(String message) {
		this.message = message;
	}

	private int getLevel() {
		return level;
	}

	private void setLevel(int level) {
		this.level = level;
	}
	private String getTime(){
		return time;
	}

}
