package Chat;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import java.awt.List;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.Choice;
import javax.swing.JLabel;

public class History {

	private JFrame frame;
	Button level_0;
	Button level_1;
	Button level_2;
	Button level_3;
	JList list;
	JTextArea selected_text; 
	JTextArea found_expressions;
	
	DefaultListModel model;
	private JLabel lblNewLabel;
	private JLabel lblIntoTheProgram;

	
	
	public void change_level(int x){
		
		

		
	}


	
	/*
	 * 
	 * Initial loader of the list
	 * 
	 */
	
	public void load_list(){
		
		
		
		for(int i=0;i<main.message.size();i++){
			Message message = main.message.get(i);
			
			//model.addElement(message);
			
			
			
			model.addElement("Level: "+message.level + ": >> "+ message.time + " " + message.message);
			
			
		}
		
		
		
		
	}
	
	/*
	 * 
	 * Sets the initial Button
	 * 
	 */
	
	
	public void initial_button(){
		
		
		if(list.getModel().getSize()>0){
			
		String initial = list.getModel().getElementAt(0).toString();
		
		
		highlight_button((int)initial.charAt(7)-48);
		
		}
		
		
		
	}
	
	public void SetSize_Big(Button button){
		
		button.setBounds(button.getX(), button.getY(), 100, 35);
		
	}
	
	public void SetSize_Normal(Button button){
		
		button.setBounds(button.getX(), button.getY(), 70, 22);
		
	}
	
	
	public void input_message(String text){
		selected_text.setText(text);
		
		
	}
	
	/*
	 * 
	 * Display the found expressions
	 * 
	 */
	
	public void found_expressions(ArrayList<Expression> content){
		
		String result = "Found expressions:\n";
		for(int i=0;i<content.size();i++){
			result+=content.get(i).category+":"+ content.get(i).expression+":"+content.get(i).level+ "\n";
			
			
		}
		
		
		found_expressions.setText(result);
		
	}
	
	
	
	public void highlight_button(int level){

		if(level==0){
			SetSize_Big(level_0);
			SetSize_Normal(level_1);
			SetSize_Normal(level_2);
			SetSize_Normal(level_3);
			
			
		}else if(level==1){
			SetSize_Big(level_1);
			SetSize_Normal(level_0);
			SetSize_Normal(level_2);
			SetSize_Normal(level_3);
			
			
		}else if(level ==2){
			SetSize_Big(level_2);
			SetSize_Normal(level_1);
			SetSize_Normal(level_0);
			SetSize_Normal(level_3);
			
			
			
		}else{
			SetSize_Big(level_3);
			SetSize_Normal(level_1);
			SetSize_Normal(level_2);
			SetSize_Normal(level_0);
			
		}
		
		
		
		
		
	}
	
	
	/*
	 * 
	 * This is the update class, in case, the user changes the level of a message
	 * This is the basic class for retraining the lexicon and the classification process.
	 * 
	 */
	
	public void retrain_all(Message message, int level_new) throws SQLException{
		
		if(message.classified==false){//this only is to be done for the lexicographic approach
		int level= message.level;
		double update_factor=1.0;
		
		if(level==0){
			
			update_factor+=  level_new/3;
			
		}else if(level==1){
			
			if(level_new==0)
				update_factor=1/2;
			else {
				if(level_new==2)
					update_factor =1.5;
					else update_factor=2;
				
				
			}
			
		}
		else if(level==2){
			
			if(level_new<level){
					if(level_new==0)
						update_factor=1/3;
					else update_factor=2/3;
			}	
			else update_factor=4/3;
			
			
		}else if(level==3){
			if(level_new==0)
				update_factor=3/4;
			else if(level_new==1)
				update_factor=2/4;
			else
				update_factor=1/4;
			
		}
		
		
			
			
		
		
		if(message.found_expressions.size()>0){
			
			for(int i=0;i<message.found_expressions.size();i++){
				
				main.update_expression(message.found_expressions.get(i), update_factor);
				
			}
			
			
		}
		
		
		update_all_messages();
		}
		
	}
	
	
	
	/*
	 * 
	 * This updates all messages
	 * 
	 */
	
	
	private void update_all_messages() {
		
		for(int i=0;i<main.message.size();i++){
			for(int j=0;j<main.message.get(i).found_expressions.size();j++){
				
				main.update_single_expression(main.message.get(i).found_expressions.get(j));
				
				
				
				
			}
			
			
			
		}
		
		
		
	}


	
	/*
	 * 
	 * Update the level the message found
	 * 
	 */

	void change_level_of_message(int level){
		
		if(main.message.size()>0){
			
			String s = model.get(list.getSelectedIndex()).toString();
			int message_position = main.find_message(s.substring(26, s.length()), s.substring(13, 25));
			
			
			main.message.get(message_position).level=level;
			
			try {
				if(main.message.get(message_position).classified==false)
				retrain_all(main.message.get(message_position), level);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
	
	
	/*
	 * 
	 * This is for the display of the button
	 * 
	 */
	
	void button_pressed(int button){
		
		change_level_of_message(button);
		highlight_button(button);
		
	}
	
	
	void listener_collection(){
		
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton()== MouseEvent.BUTTON2){
					JTextComponent myComponent = (JTextComponent) arg0.getComponent();
					String message = myComponent.getSelectedText();

					
					
				}
				
			}
		});
		
		
		/*
		 * 
		 * This is a class for adding content
		 * 
		 */
		
		
		selected_text.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(SwingUtilities.isRightMouseButton(e)){
					String highlight = selected_text.getSelectedText();
					if(highlight.length()>0)
						if(!main.expression_exists(highlight)){
							
							
							new Add_Content(e.getX(),e.getY(),highlight);
						
								
							
							
						}else{
							
							
							JOptionPane.showMessageDialog(frame, "Expression "+ highlight +" already exists in the dictionary.");
						}
						
					
					
				}
				
			}
			
			
			
			
			
		});
		
		
		
		/*
		 * 
		 * This are the four classes in case a button was pressed
		 * 
		 */
		
		level_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button_pressed(0);
				
				
				
			}
		});
		level_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				button_pressed(1);
			}
		});
		level_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				button_pressed(2);
			}
		});
		level_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				button_pressed(3);
			}
		});
		
		
		/*
		 * 
		 * This is the method, when a new message was selected
		 * 
		 */
		
		 ListSelectionListener listSelectionListener = new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				
				String s = model.get(list.getSelectedIndex()).toString();
				
				highlight_button(s.charAt(7)-48);
				
				
				
				int position_in_list= main.find_message(s.substring(26, s.length()), s.substring(13, 25));
			
				
				if(main.message.get(position_in_list).classified==false){
				
				input_message(s.substring(26, s.length()));

				found_expressions( main.message.get(position_in_list).found_expressions);
			
				}else{
					
					selected_text.setText("");
					found_expressions.setText("");
					
				}
				
				
				
			}
			 
			 
			 
		 };
		list.addListSelectionListener(listSelectionListener);
	
		
		
		
	}
	
	
	
	public History() {
		initialize();
		load_list();
		listener_collection();
		initial_button();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 728);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		model = new DefaultListModel();
		list = new JList(model);

		list.setBounds(77, 11, 619, 324);
		
		frame.getContentPane().add(list);
		
		level_0 = new Button("Level 0");
	
		level_0.setBounds(77, 599, 70, 22);
		frame.getContentPane().add(level_0);
		
		level_1 = new Button("Level 1");

		level_1.setBackground(Color.GREEN);
		level_1.setBounds(234, 599, 70, 22);
		frame.getContentPane().add(level_1);
		
		level_2 = new Button("Level 2");
	
		level_2.setBackground(Color.YELLOW);
		level_2.setBounds(416, 599, 70, 22);
		frame.getContentPane().add(level_2);
		
		level_3 = new Button("Level 3");
	
		level_3.setBackground(Color.RED);
		level_3.setBounds(575, 599, 70, 22);
		frame.getContentPane().add(level_3);
		
		found_expressions = new JTextArea();
		found_expressions.setBounds(465, 403, 231, 100);
		found_expressions.setAutoscrolls(true);
		frame.getContentPane().add(found_expressions);
		
		selected_text = new JTextArea();
		selected_text.setBounds(78, 403, 377, 100);
		selected_text.setAutoscrolls(true);
		frame.getContentPane().add(selected_text);
		
		lblNewLabel = new JLabel("Please select text and the right mouse-button to import the expression");
		lblNewLabel.setBounds(77, 514, 378, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblIntoTheProgram = new JLabel("into the program.");
		lblIntoTheProgram.setBounds(77, 527, 378, 14);
		frame.getContentPane().add(lblIntoTheProgram);
		frame.setVisible(true);
	}
}
