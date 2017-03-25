package Chat;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import javax.swing.DropMode;
import java.awt.TextField;
import java.awt.ScrollPane;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextArea;
import javax.swing.JTextField;

public class GUI {

	private JFrame frame;
	JTextArea textField;
	Button send;
	Button history;
	Button price_yes;
	Button price_no;
	JTextArea price_area;
	JTextArea chat_history;
	JButton button_setprice;
	JTextField set_price;
	JLabel Label_Info;
	JButton actual_prices;
	JButton acc_yes;
	JButton acc_no;
	String message;
	int detected_level;

	
	SimpleDateFormat sdf = new SimpleDateFormat("EE, hh:dd:ss");
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat bla;
	
	int x=-1;
	
	
	
	
	/*
	 * 
	 * This is the calculation of the privacy level, where all the weighting is calculated
	 * 
	 */
	
	
	
	int sensitivity(ArrayList<Expression> result){
		
		//no found expressions: level 0
		if(result.size()==0) return 0;
		
		else{
			
			double x=0;
		//else: average out of all levels
		for(int i=0;i<result.size();i++){
			x+=result.get(i).level;
			
		}
			
		
			
			
		return (int)x/result.size();	
			
		}
		
		
		
		
	}
	
	
	
	/*
	 * 
	 * This is the finding and the detection of the privacy tags within the sentence
	 * 
	 */
	
	ArrayList<Expression> get_expressions(String text){
		
		
		text=text.toLowerCase();
		ArrayList<Expression> result = new ArrayList<Expression>();
		
		
		for(int i=0;i<main.expression.size();i++){
			
			if(text.contains(main.expression.get(i).expression))
			result.add(main.expression.get(i));
			
		}
		
		
		
		return result;
		
	}
	
	
	
	/*
	 * 
	 * This sets the initial prices after input by the user
	 * 
	 */
	
	void set_price_initially(double input) throws SQLException{
		
		int detected_level = main.message.get(main.message.size()-1).level;
		
		if(detected_level==1){
			
			main.price.put(1, new Price(input, input/5, input/40, 0));
			main.price.put(2,new Price(input*2, input*1.5/5, input/40, 0));
			main.price.put(3,new Price( input*3, input*2/5, input/40, 0));
			
			main.write_price_initial();
			
			
		} else if(detected_level==2){
			
			main.price.put(1,new Price(input*0.5, input*0.7/5, input/40, 0));
			main.price.put(2,new Price(input, input/5, input/40, 0));
			main.price.put(3,new Price( input*1.5, input*1.3/5, input/40, 0));
			main.write_price_initial();
			
		}else{
			
			main.price.put(1,new Price(input/3, input*0.5/5, input/40, 0));
			main.price.put(2,new Price(input/2, input*0.7/5, input/40, 0));
			main.price.put(3,new Price(input, input/5, input/40, 0));
			main.write_price_initial();
			
		}
		
		
	
		
	}
	
	/*
	 * 
	 * Helper class for displaying the price
	 * 
	 */
		
	
	double round(double val) {
        DecimalFormat df2 = new DecimalFormat("#######.##");
        return Double.valueOf(df2.format(val));
	}
	
	
	void listener_collection(){
		
		
		
		chat_history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton()== MouseEvent.BUTTON2){
					JTextComponent myComponent = (JTextComponent) arg0.getComponent();
				
					
					
					
				}
				
			}
		});
		

		
		/*
		 * 
		 * History window is not updatable, this sets some warning
		 * 
		 */
		
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new History();
				String s = chat_history.getText();
				s+="----------------------------------------- ----------------\n";
				s+="Detection may have changed here, entries are not accurate.\n";
				s+="----------------------------------------------------------\n";
				chat_history.setText(s);
				
				
				
				
				
				
			}
		});
		/*
		 * 
		 * This sets the initial Price in the GUI
		 * 
		 */
		
		actual_prices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			String message_price="";
			if(main.price.size()==0)
				message_price="No price has been set yet.";
			else{
				for(int i=1;i<=3;i++)
				message_price+="prices:\nLevel+"+i+": "+main.price.get(i).price+"\n";
				
				}
				JOptionPane.showMessageDialog(frame, message_price);
			}
			
			
		});

		
		
		button_setprice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(main.message.size()==0){
					price_area.setText("Please provice a Message first.");
					
				}
				else if(main.message.get(main.message.size()-1).level==0){
					
					price_area.setText("Please provide a message with privacy sensitive information first.");
				}
				else{
				
				String text = set_price.getText();
				if(text.length()==0)
					price_area.setText("Please enter a price and click the button again.");
				else{
					
					try{
						
						Double price=Double.parseDouble(text);
						
						try {
							set_price_initially(price);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						deactivate();
						
						
						
					}catch(NumberFormatException e){
						
						set_price.setText("");
						price_area.setText("This was not a correct input. Please put in a decimal Number.");
						
						
						
					}
					
					
				}
				}
			}
		});
		
		
		/*
		 * 
		 * User accepts the price. This is based on the last input message
		 * 
		 */
		
		
		
		
		acc_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String time = sdf.format(cal.getTime());
				
				main.message.add(new Message(message, detected_level, time, true));
				
				acc_no.setVisible(false);
				acc_yes.setVisible(false);
				frame.repaint();
				
			}
		});
		acc_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Expression> found_expressions=get_expressions(message);
				String time = sdf.format(cal.getTime());
				int sensitivity = sensitivity(found_expressions);
				
				
				main.message.add(new Message(message, sensitivity, time, found_expressions, false));
				
				
				
				if(chat_history.getText().equals(""))
					chat_history.setText( ">> "+ time +" "+  message+"; Level: "+sensitivity+"\n");	
				else
					chat_history.setText(chat_history.getText() + ">> "+ time +" "+ message +"; Level: "+sensitivity+"\n");
				textField.setText("");
				
				
				if(sensitivity>0){
				
				if (main.price.size()>0){
					
				
				price_area.setText("We have detected a message of level "+sensitivity +". We would like to give to you a value of \n "+ main.price.get(sensitivity).price+ "\nfor the usage of this Information. Would you agree?");
				}
				else{
					
				price_area.setText("We have detected a message of level "+sensitivity+". \nWe would like to know, what this message is worth to you. Please fill in a Number in the right field and press the button");	
					
					
				}
					
				
			}
				
				
				acc_no.setVisible(false);
				acc_yes.setVisible(false);
				frame.repaint();
				
				
				
				
			}
		});
		
		
		price_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(main.message.size()==0)
					price_area.setText("No Input of a message yet. Please first type in a message.");
				else if(main.message.get(main.message.size()-1).level==0)
					price_area.setText("Input message has a level of 0. We can't offer you money for this information");
				else{
					
					
					int level = main.message.get(main.message.size()-1).level;
					Price price=main.price.get(level);
					
				
						
						int direction=-1;
						
						if(price.direction==0){ direction =-1;
						price.direction=-1;
						}
						
						if(direction!=price.direction){
							price.direction=-1;
							price.changing=price.changing/2;
						}
			
						
						
						if(price.changing<=price.treshold){
							
							price.price=price.price/(price.price-price.changing);
						}
					
						try {
							main.update_price(level, price);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					
					price_area.setText("Thank you for your response. Your price has been set.");
				}
				
				
				
				
				
				
				
				
			}
		});
		
		/*
		 * 
		 * User doesn't accept the price. This is based on the last input message
		 * 
		 */
		price_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(main.message.size()==0)
					price_area.setText("No Input of a message yet. Please first type in a message.");
				else if(main.message.get(main.message.size()-1).level==0)
					price_area.setText("Input message has a level of 0. We can't offer you money for this information");
				else{
					
					
					int level = main.message.get(main.message.size()-1).level;
					Price price=main.price.get(level);
					
				
						
						int direction=1;
						
						if(price.direction==0){ direction =1;
						price.direction=1;
						}
						
						if(direction!=price.direction){
							price.direction=1;
							price.changing=price.changing/2;
						}
			
						
						
						if(price.changing<=price.treshold){
							
							price.price=price.price+price.changing;
						}
					
						try {
							main.update_price(level, price);
						} catch (SQLException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					
					
					
					price_area.setText("Thank you for your response. Your price has been set.");
				}
			}
		});
		
		
		/*
		 * 
		 * User provides a new message
		 * 
		 */
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				message = textField.getText();
				
				detected_level = 1;
				double accuracy = 33.9;
				String str= textField.getText()+"\n\nThe message was classified as level "+ detected_level +" and an accuracy of "+ accuracy+". Is this acceptable to you?";
				textField.setText(str);
				
				frame.repaint();
				System.out.println(str);
				acc_yes.setVisible(true);
				acc_no.setVisible(true);
				frame.repaint();
				
				
			}
		});
	}
	
	
	/*
	 * 
	 * If a price has been set initially once, this routine eliminates the input windows
	 * 
	 */
	public void deactivate(){
		
		frame.remove(set_price);
		frame.remove(button_setprice);
		frame.revalidate();
		frame.repaint();
		price_area.setText("After a Message with level 1-3 is provided, you will be offered a price for it.");
		
		
	}
	
	
	

	/**
	 * Create the application.
	 */
	public GUI() {
		
		try{
		initialize();
		listener_collection();
		if(!main.price.isEmpty()){
			
		
			deactivate();
		
		
		}else{
			price_area.setText("Welcome to the pricing system.\nAfter providing a message that has been classified witin the levels 1-3, you can set an \ninitial price. The prices for the other categories will be set depending on your first choice.\nPlease set a price of at least 10,00");
			
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
			}
			
		
		}
	

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 885, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextArea();

		textField.setBounds(23, 339, 569, 244);
		frame.getContentPane().add(textField);
		
		history = new Button("History");

		history.setBounds(706, 152, 70, 22);
		frame.getContentPane().add(history);
		
		send = new Button("Send");

		send.setBounds(693, 516, 70, 22);
		frame.getContentPane().add(send);
		
		chat_history = new JTextArea();
		chat_history.setEditable(false);
		chat_history.setAutoscrolls(true);
		chat_history.setBounds(23, 36, 569, 266);
		chat_history.setLineWrap(true);
		
		frame.getContentPane().add(chat_history);
		
		price_area = new JTextArea();
		price_area.setEditable(false);
		price_area.setBounds(23, 600, 569, 65);
		price_area.setAutoscrolls(true);
		price_area.setLineWrap(true);
		frame.getContentPane().add(price_area);
		
		
		
		price_yes = new Button("Yes");

		price_yes.setBounds(114, 690, 70, 22);
		frame.getContentPane().add(price_yes);
		
		price_no = new Button("No");
	
		price_no.setBounds(371, 690, 70, 22);
		frame.getContentPane().add(price_no);
		
		button_setprice = new JButton("Set Price");

		button_setprice.setBounds(759, 615, 89, 23);
		frame.getContentPane().add(button_setprice);
		
		set_price = new JTextField();
		set_price.setToolTipText("enter price here");
		set_price.setBounds(614, 616, 114, 32);
		frame.getContentPane().add(set_price);
		set_price.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Accept offered Price?");
		lblNewLabel.setBounds(205, 690, 138, 22);
		frame.getContentPane().add(lblNewLabel);
		
		Label_Info = new JLabel("Plase only use these buttons after an offer was made to you");
		Label_Info.setBounds(457, 690, 332, 22);
		frame.getContentPane().add(Label_Info);
		
	

		
		actual_prices = new JButton("show actual prices");
		actual_prices.setBounds(624, 656, 169, 23);
		frame.getContentPane().add(actual_prices);

		
		acc_yes = new JButton("yes");

		acc_yes.setBounds(608, 390, 89, 23);
		acc_yes.setVisible(false);
		frame.getContentPane().add(acc_yes);
		
		acc_no = new JButton("no");
		acc_no.setBounds(608, 457, 89, 23);
		acc_no.setVisible(false);
		frame.getContentPane().add(acc_no);
		
		frame.setVisible(true);
		
	}
}
