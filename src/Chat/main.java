package Chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class main {

	static ArrayList<Message> message;
	static Connection conn;
	static ArrayList<Expression> expression;
	static ArrayList<String> category= new ArrayList<String>();
	static HashMap <Integer, Price> price = new HashMap <Integer, Price>();
	static Statement statement;
	boolean gui_needed=true;
	static Classification classification = new Classification();
	
	
	
	
	/*
	 * 
	 * Constructor, in case, no GUI is needed
	 * 
	 */
	public main(boolean gui_needed){
		this.gui_needed=gui_needed;
		
	}
	
	
	
	
	/*
	 * 
	 * Empty Constructor for all other cases
	 * 
	 */
	public main(){
		
		
	}
	
	
	/*
	 * 
	 * Loads the different categories
	 * 
	 */
	
	
	void load_category(){
		
		
		category.add("general");
		category.add("politics");
		category.add("ethnicity");
		category.add("religion");
		category.add("philosophical");
		category.add("genetic data");
		category.add("biometrics");
		category.add("sexual orientation");
		category.add("health");
		
	}
	
	
	/*
	 * 
	 * Connection to the database
	 * 
	 */
	
	
	 public static void connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:privacy.db");
			statement = conn.createStatement();
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			
		
			
			
		}
			
	}
	 
	 public static double level_of_expression(Expression exp){
		 
		 for(int i=0;i<expression.size();i++){
			 if(expression.get(i).category.equals(exp.category)&&expression.get(i).expression.equals(exp.expression))
				 return expression.get(i).level;
			 
		 }
		 return 0;
		 
	 }
	 
	 
	 public static void update_single_expression(Expression exp){
		 
		 exp.level= level_of_expression(exp);
		 
	 }
	 
	 
		/*
		 * 
		 * Set a net Factor for a expression. Updates as well in the Expression ArrayList as well as in The 
		 * 
		 */
	 
	 public static void update_expression(Expression exp, double update_factor) throws SQLException{
		 
		 
		 double new_level = expression.get(find_expression(exp)).level*update_factor;
		 if(new_level>3) new_level=3.0;
		 
		 
		 
		expression.get(find_expression(exp)).level=new_level;
		 
		 String queue="Update privacy SET level="+new_level+" WHERE tag="+exp.expression+" AND category="+exp.category+";";
		 
		 statement.execute(queue);
	 }
	 
	 
		/*
		 * 
		 * Sets the initial price
		 * 
		 */
	 
	public static void write_price_initial() throws SQLException{
		
		
		String result;
		
		//Set the initial price to the Database
		
		for (int i=1;i<=price.size();i++){
			result ="INSERT INTO price('level', 'price', 'Changing_Factor', 'treshold', 'direction') VALUES";
			result+="(" + (i) + ", " + price.get(i).price+", "+price.get(i).changing+", "+ price.get(i).treshold+", "+ price.get(i).direction + ");";
			
			statement.execute(result);
			
			
			
		}
		
		
	} 
	
	
	/*
	 * 
	 * Method for finding an Expression in the ArrayList
	 * 
	 */
	public static int find_expression(Expression exp){
		
		for(int i=0;i<expression.size();i++){
			if(expression.get(i).category==exp.category && expression.get(i).expression==exp.expression)
				return i;
			
			
		}
		return -1;
		
	}
	
	
	
	/*
	 * 
	 * price Update for the ArrayList and for the Database
	 * 
	 */
	
	public static void update_price(int level, Price tprice) throws SQLException{
		
		//Updates the Price in the price Hashmap
		price.get(level).changing=tprice.changing;
		price.get(level).price=tprice.price;
		price.get(level).treshold=tprice.treshold;
		price.get(level).direction=tprice.direction;

		//Update the price in the database
		String queue = "UPDATE price SET price ="+tprice.price+" AND Changing_Factor="+tprice.changing;
		queue+=" AND treshold="+tprice.treshold+" AND direction="+tprice.direction+" WHERE level="+level+";";
			statement.execute(queue);
		
		
	}
	 
	 
	/*
	 * 
	 * Checks if an expression exists
	 * 
	 */
	
	public static boolean expression_exists(String found_expression){
		
		//find expression
		for(int i=0;i<expression.size();i++){
			if(expression.get(i).expression.equals(found_expression))
				return true;
			
		}
		return false;
		
	}
	
	
	
	/*
	 * 
	 * Finds the position of a message, returns -1 if the message was not found
	 * 
	 */
	 
	 public static int find_message(String message_user, String time){
	
		 //find the position of a message
		 for(int i=0;i<message.size();i++){
			 if(message.get(i).message.equals(message_user) && message.get(i).time.equals(time))
				 return i;
			 
			 
		 }
		 return -1;
		 
		 
		 
		 
	 }
	 
	 

		/*
		 * 
		 * Load the price from the database
		 * 
		 */
	 
	 public void load_price(){
		 
	
		 
		 String execute = "SELECT * From price";
		 
			 try {
				ResultSet rs = statement.executeQuery(execute);
				
				while(rs.next()){
					
					
					price.put(rs.getInt(1), new Price( rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 
		 
		 
	 }
	 
	 
	 
		/*
		 * 
		 * Load the Expression list from the Database
		 * 
		 */
	 
	 public void fill_expression(){
		 
		
		 
		 String execute = "SELECT * FROM privacy";
		 ResultSet rs;
		 String tag;
		 
		 try {
			rs = statement.executeQuery(execute);
			 while(rs.next()){
				 
				 
				 
				 tag = rs.getString("tag");
				 tag=tag.replaceAll("#", "'"); //SQL does not support apostrophs, this why the expressions were changed to #, this changes it back to '
				 
				 
					expression.add(new Expression(tag, rs.getString("category"), rs.getDouble(2)     ));
					 
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	
		 
		 
		 
		 
	 }
	 
	 public static boolean expression_and_category_exist(String text, String cat){
		 
		
		 
		 for(int i=0;i<expression.size();i++){
			if(expression.get(i).expression.equals(text)&&expression.get(i).category.equals(cat))
				return true;
			 
			 
		 }
		 
		 return false;
		 
	 }
	 
	 
	 public static void set_into_privacy(String text, String cat, int lvl){
		 
		 
			 
			 
			 
			 if(!expression_and_category_exist(text, cat)){
				 double dbl=lvl;
			 String exec = "INSERT INTO privacy('tag', 'level', 'category') VALUES('"+text+"', "+dbl+", '"+cat+"');";
				 
			 
				 try {
					statement.execute(exec);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 expression.add(new Expression(text, cat, dbl));
				 
				 
			 
			 
			 
		 }
		 
		 
		 
		 
		 
	 }
	 
		/*
		 * 
		 * Find the message
		 * 
		 */
	 
	 static Message getMessage(String message, String time){
		 Message mess_return =null;
		 Message temp=null;
		for(int i=0;i<main.message.size();i++){
			temp=main.message.get(i);
			if(temp.message.equals(message) && temp.time.equals(message))
				break;
			
		}
		 mess_return = temp;
		 
		 
		 return mess_return;
	 }
	 
	 
	 
	 
		/*
		 * 
		 * Initial Load
		 * 
		 */

void load(){

	//initial load at beginning
	message = new ArrayList<Message>();
	expression = new ArrayList<Expression>();
	
	
	connect();

	load_category();

	fill_expression();
	
	load_price();


	
}
	 


	void initialize() {
		load();
		if(gui_needed)
		new GUI();

	}

	
	
	public static void main(String[] args) {

		new main().initialize();

	}

}
