package Chat;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
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
	TextField textField;
	Button send;
	Button history;
	Button price_yes;
	Button price_no;
	JTextArea price_area;
	JTextArea chat_history;
	JButton button_setprice;
	JTextField set_price;
	
	
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
		
		if(result.size()==0) return 0;
		
		else{double x=0;
		
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
	
	
	
	void set_price_initially(double input){
		
		int detected_level = main.message.get(main.message.size()-1).level;
		
		if(detected_level==1){
			
			main.price.put(1, new Price(input, input/5));
			main.price.put(2,new Price(input*1.5, input*1.5/5));
			main.price.put(3,new Price( input*2, input*2/5));
			
			
			
			
		} else if(detected_level==2){
			
			main.price.put(1,new Price(input*0.7, input*0.7/5));
			main.price.put(2,new Price(input, input/5));
			main.price.put(3,new Price( input*1.3, input*1.3/5));
			
			
		}else{
			
			main.price.put(1,new Price(input*0.5, input*0.5/5));
			main.price.put(2,new Price(input*0.7, input*0.7/5));
			main.price.put(3,new Price(input, input/5));
			
			
		}
		
		
	
		
	}
	
	
	
	
	void listener_collection(){
		
		
		
		chat_history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton()== MouseEvent.BUTTON2){
					JTextComponent myComponent = (JTextComponent) arg0.getComponent();
					String message = myComponent.getSelectedText();
					System.out.println(message);
					
					
				}
				
			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new History();
				String s = chat_history.getText();
				s+="------------------------------------------------\n";
				s+="Detection may have changed here, entries are not accurate.\n";
				s+="------------------------------------------------\n";
				chat_history.setText(s);
				
				
				
				
				
				
			}
		});
		
		button_setprice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String text = set_price.getText();
				if(text.length()==0)
					price_area.setText("Please enter a price and click the button again.");
				else{
					
					try{
						
						Double price=Double.parseDouble(text);
						
						set_price_initially(price);
						deactivate();
						
						
						
					}catch(NumberFormatException e){
						
						set_price.setText("");
						price_area.setText("This was not a correct input. Please put in a decimal Number.");
						
						
						
					}
					
					
				}
				
			}
		});
		
		
		
		
		price_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				price_area.setText("Thank you for your response.");
				
				
			}
		});
		price_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				price_area.setText("Thank you for your response.");
			}
		});
		
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = textField.getText();
				ArrayList<Expression> found_expressions=get_expressions(message);
				String time = sdf.format(cal.getTime());
				int sensitivity = sensitivity(found_expressions);
				
				
				main.message.add(new Message(message, sensitivity, time, found_expressions));
				
				
				
				if(chat_history.getText().equals(""))
					chat_history.setText( ">> "+ time +" "+  message+"; Level: "+sensitivity+"\n");	
				else
					chat_history.setText(chat_history.getText() + ">> "+ time +" "+ message +"; Level: "+sensitivity+"\n");
				textField.setText("");
				
				
				if(sensitivity>0){
				
				if (main.price.size()>0){
					
				
				price_area.setText("We have detected a message of level "+sensitivity +". We would like to give to you a value of \n "+ "100,00$"+ "\n for the usage of this Information. Would you agree?");
				}
				else{
					
				price_area.setText("We have detected a message of level "+sensitivity+". \n We would like to know, what this message is worth to you. Please fill in a Number in the right field and press the button");	
					
					
				}
					
				
			}
				
			}
		});
	}
	
	public void deactivate(){
		
		frame.remove(set_price);
		frame.remove(button_setprice);
		frame.revalidate();
		frame.repaint();
		
	}
	
	
	

	/**
	 * Create the application.
	 */
	public GUI() {
		
		try{
		initialize();
		listener_collection();
		if(!main.price.isEmpty())
			deactivate();
		
		
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
		
		textField = new TextField();

		textField.setBounds(33, 339, 569, 244);
		frame.getContentPane().add(textField);
		
		history = new Button("History");

		history.setBounds(706, 152, 70, 22);
		frame.getContentPane().add(history);
		
		send = new Button("Send");

		send.setBounds(693, 516, 70, 22);
		frame.getContentPane().add(send);
		
		chat_history = new JTextArea();
		chat_history.setEditable(false);
		chat_history.setBounds(23, 36, 569, 266);
		chat_history.setLineWrap(true);
		
		frame.getContentPane().add(chat_history);
		
		price_area = new JTextArea();
		price_area.setEditable(false);
		price_area.setBounds(23, 600, 569, 65);
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
		frame.setVisible(true);
		
	}
}
