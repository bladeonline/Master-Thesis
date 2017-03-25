package Chat;

import java.util.ArrayList;

public class Message {
	String message;
	int level;
	String time;
	ArrayList <Expression> found_expressions;
	Boolean classified;
	

	public Message(String message, int level, String time, Boolean classified) {
		this.message = message;
		this.level = level;
		this.time=time;
		this.classified=classified;

	}
	
	public Message(String message, int level, String time, ArrayList<Expression> found_expression, Boolean classified) {
		this.message = message;
		this.level = level;
		this.time=time;
		this.found_expressions=found_expression;
		this.classified=classified;
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
