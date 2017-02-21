package Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Training {

	private JFrame frame;
	static int i=0;
	
	public void set_text(){
		
	
		while(i<main.expression.size() && main.expression.get(i).level!=-1){
			i++;
			}
		
		
		if(i==main.expression.size()){
			textArea.setText("The training is done.");
		
		}
		else
		
		textArea.setText("     Category:\n"+main.expression.get(i).category+"\n     Tag:"+main.expression.get(i).expression);
			
	}
	
	
	
	
	JButton Button_2;
	JButton Button_0;
	JButton Button_1;
	JButton Button_3;
	JButton Button_Start;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Training window = new Training();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

		});

		
		

	}
	
	void set_level(int level, String tag, String category){
		
		
		tag = tag.replaceAll("'","#" );
		
		String answer = "UPDATE privacy_tag SET level="+level+" WHERE tag='"+tag+"' AND category='"+category+"';";
		
		try {
			main.statement.execute(answer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	void set_listener(){
		
		Button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_level(3, main.expression.get(i).expression, main.expression.get(i).category       );  
				i++;
				set_text();
				
			}
		});
		
		Button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_level(2, main.expression.get(i).expression, main.expression.get(i).category       );  
				i++;
				set_text();
			}
		});
		
		Button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_level(1, main.expression.get(i).expression, main.expression.get(i).category       );  
				i++;
				set_text();
			}
		});
		Button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_level(0, main.expression.get(i).expression, main.expression.get(i).category       );  
				i++;
				set_text();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Training() {
		
		initialize();
		main main= new main();
		main.load();
		set_text();
		set_listener();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		

		
		
		Button_0 = new JButton("o");

		Button_0.setBounds(31, 298, 89, 23);
		frame.getContentPane().add(Button_0);
		
		Button_1 = new JButton("1");

		Button_1.setBounds(159, 298, 89, 23);
		frame.getContentPane().add(Button_1);
		
		Button_2 = new JButton("2");

		Button_2.setBounds(289, 298, 89, 23);
		frame.getContentPane().add(Button_2);
		
		Button_3 = new JButton("3");

		Button_3.setBounds(431, 298, 89, 23);
		frame.getContentPane().add(Button_3);
		
		Button_Start = new JButton("Start Training");
		

		Button_Start.setBounds(232, 11, 177, 23);
		frame.getContentPane().add(Button_Start);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(125, 100, 335, 116);
		frame.getContentPane().add(textArea);
	}
}
