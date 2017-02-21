package Chat;

import java.util.ArrayList;

public class Expression {

	String expression;
	String category;
	int level;
	public Expression(String expression, String category, int level) {
		super();
		this.expression = expression;
		this.category = category;
		this.level = level;
	}
	private String getexpression() {
		return expression;
	}
	private void setexpression(String expression) {
		this.expression = expression;
	}
	private String getCategory() {
		return category;
	}
	private void setCategory(String category) {
		this.category = category;
	}
	private int getLevel() {
		return level;
	}
	private void setLevel(int level) {
		this.level = level;
	}
	
	public ArrayList<String> contains(String sentence){
		
		ArrayList<String> result= new ArrayList<String>();
		
		
		
		
		
		return result;
		
		
	}
	
	
	
}
