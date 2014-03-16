package hw_03;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class BMI extends JFrame{
	//Create text fields for age , weight , height , BMI
	private JTextField jtfAge = new JTextField(); 
	private JTextField jtfWeight = new JTextField();
	private JTextField jtfHeight = new JTextField();
	private JTextField jtfBMI = new JTextField();
	
	//Create a Compute BMI button
	private JButton jbtComputeBMI = new JButton("Compute BMI");
	
	private String name;
	private int age;
	private double weight;//in pounds
	private double heights;//in inches
	public static final double KILOGRAMS_PER_POUND = 0.45359237;
	public static final double METERS_PER_INCH = 0.0254;
	
	//constructor
	public BMI(String name, int age, double weight, double heights){
		this.name = name ;
		this.age = age ;
		this.weight = weight ;
		this.heights = heights ;
	}
	
	//constructor
	public BMI(String name, double weight, double heights){
		this(name,20,weight,heights);
	}
	
	//to getBMI  
	public double getBMI(){
		double bmi = (weight * KILOGRAMS_PER_POUND) / (Math.pow((heights * METERS_PER_INCH),2));
		//Math.round return Math.floor(x +0.5)
		return Math.round(bmi * 100) / 100.0;
	}
	
	//to getStatus
	public String getStatus(){
		double bmi = getBMI();
		if(bmi < 18.5)
			return "Underweight";
		else if(bmi < 25)
			return "Normal";
		else if(bmi < 30)
			return "Overweight";
		else 
			return "Obese";
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public double getHeights(){
		return heights;
	}
	
	public BMI(){
		//set GridLayout , 5 rows , 2 columns , and gaps 5 between 
		//components horizontally and vertically 
		//Panel p1 to hold labels and text fields
		JPanel p1 = new JPanel(new GridLayout(5,2,5,5));
		
		p1.add(new JLabel("Name"));
		p1.add(new JTextField(8));
		p1.add(new JLabel("Age"));
		p1.add(jtfAge);
		p1.add(new JLabel("Weight"));
		p1.add(jtfWeight);
		p1.add(new JLabel("Height"));
		p1.add(jtfHeight);
		p1.add(new JLabel("BMI"));
		p1.add(jtfBMI);
		p1.setBorder(new TitledBorder("Enter Name , Age , Weight , Height"));
		
		//Panel p2 to hold the button
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(jbtComputeBMI);
		
		//Add the panels to the frame
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		//register listener
		jbtComputeBMI.addActionListener(new ButtonListener());
	}
	
	//Handle the ComputeBMI button
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			//Get values from text fields 
			int age = Integer.parseInt(jtfAge.getText());
			double weight = Double.parseDouble(jtfWeight.getText());
			double heights = Double.parseDouble(jtfHeight.getText());
			
			//create a bmi object 
			BMI bmi = new BMI(name,age,weight,heights);
			
			//display BMI
			jtfBMI.setText(String.format("%.2f",bmi.getBMI()));
			
		}
	}
	
	public static void main(String[] args){
		BMI frame = new BMI();
		frame.setTitle("BMI");
		frame.setSize(200,200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
