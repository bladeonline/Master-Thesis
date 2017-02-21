package Chat;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Add_Content {

	private JFrame frame;
	JComboBox level; 
	JComboBox category;
	JTextArea text;
	JButton button_save;

	/**
	 * Launch the application.
	 */

	
	public void load(){
		
//		for(int i=0;i<main.category.size();i++)
//		category.addItem(main.category.get(i));
		category.addItem("bla");
		category.addItem("blub");
		
		int [] different_levels = new int []{0,1,2,3}; 
		for(int i=0;i<different_levels.length;i++)
		level.addItem(different_levels[i]);
		
		
		
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		System.out.println("Level: "+level.getSelectedItem()+", Category: "+category.getSelectedItem()  );
				
			//	frame.dispose();
				
				
			}
		});
		
		
		
		}
	public void set_text(String text_input){
		text.setText("The word "+text_input + " is not yet part of the detection system.\nPlease select a privacy level and a category and klick Okay.");
		
		
	}
	
	

	
	
	
	
	

	/**
	 * Create the application.
	 */
	public Add_Content() {
		initialize(100,100);
	}
	public Add_Content(int a, int b, String text) {
		
		initialize(a,b);
		load();
		set_text(text);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int a, int b) {
		frame = new JFrame();
		frame.setBounds(a,b, 368, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		text = new JTextArea();
		text.setEditable(false);
		text.setBounds(10, 0, 321, 47);
		text.setLineWrap(true);
		frame.getContentPane().add(text);
		
		category = new JComboBox();
		category.setBounds(10, 58, 134, 20);
		frame.getContentPane().add(category);
		
		
		
		level = new JComboBox();
		level.setBounds(177, 58, 143, 20);
		frame.getContentPane().add(level);
		
		button_save = new JButton("Save");
		button_save.setBounds(121, 103, 89, 23);
		frame.getContentPane().add(button_save);
		frame.setVisible(true);
	}
}
