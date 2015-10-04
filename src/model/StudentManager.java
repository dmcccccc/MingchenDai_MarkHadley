package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentManager extends JPanel implements Serializable {
	private ArrayList<Student> allStudents;
	private Student currentStudent;
	private int maxPlayTime;
	private int maxPlaysPerDay;
	
	private JLabel currentUserLabel;
	private JLabel currentUserTimeRemaining;
	private JLabel currentUserPlaysRemaining;
	
	private JButton loginButton;
	private JButton logoutButton;
	
	private JTextField idTextField;
	private JTextField passTextField;
	
	
	public StudentManager(ArrayList<Student> a) {
		this();
		for (int i = 0; i < a.size(); i++){
			allStudents.add(a.get(i));
		}
	}
	
	public StudentManager(){
		maxPlayTime=90000;
		maxPlaysPerDay = 3;
		currentStudent = null;
		allStudents = new ArrayList<Student>();
		allStudents.add(new Student("Chris","1"));
		allStudents.add(new Student("Devon", "22"));
		allStudents.add(new Student("River", "333"));
		allStudents.add(new Student("Ryan", "4444"));
		
		setupGUI();
		registerListeners();
	}
	
	private void registerListeners() {
		loginButton.addActionListener(new LoginButtonListener());
		logoutButton.addActionListener(new LogoutButtonListener());
		
	}

	public void setupGUI(){
		this.setLayout(new BorderLayout());
		currentUserLabel = new JLabel("No Current User");
		currentUserPlaysRemaining = new JLabel();
		currentUserTimeRemaining = new JLabel();
		
		JPanel userInfo = new JPanel();
		userInfo.setLayout(new GridLayout(3,0));
		
		
		currentUserLabel.setLocation(10,10);
		currentUserPlaysRemaining.setLocation(10,20);
		currentUserTimeRemaining.setLocation(10,30);
		userInfo.add(currentUserLabel);
		userInfo.add(currentUserPlaysRemaining);
		userInfo.add(currentUserTimeRemaining);
		
		loginButton = new JButton("Logon");
		logoutButton = new JButton("Logout");
		idTextField = new JTextField("enter name");
		idTextField.setSize(idTextField.getPreferredSize());
		passTextField = new JTextField("enter password");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,4));
		buttonPanel.add(loginButton);
		buttonPanel.add(logoutButton);
		buttonPanel.add(idTextField);
		idTextField.addMouseListener(new MouseAdapter(){ 
			@Override
			public void mouseClicked(MouseEvent e){
				idTextField.setText("");
			}
		});
		
		passTextField.addMouseListener(new MouseAdapter(){ 
			@Override
			public void mouseClicked(MouseEvent e){
				passTextField.setText("");
			}
		});
		buttonPanel.add(passTextField);
		
		
		//thisPanel.setSize(400,100);
		this.add(userInfo,BorderLayout.CENTER);
		this.add(new JLabel("Current User Info"), BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setSize(400,100);
		this.setLocation(10,300);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void writeObject(ObjectOutputStream out)
		     throws IOException{
		out.writeObject(allStudents);
		out.writeObject(currentStudent);
		out.writeInt(maxPlayTime);
		out.writeInt(maxPlaysPerDay);
		
	}
	
	 @SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		 allStudents = (ArrayList<Student>) in.readObject();
		 currentStudent = (Student) in.readObject();
		 maxPlayTime = in.readInt();
		 maxPlaysPerDay = in.readInt();
	 }

	
	private class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			login(idTextField.getText(),passTextField.getText());	
		}
	}
	
	private class LogoutButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			logout();
		}
	}
	
	public boolean login(String id, String password){
		currentStudent = null;
		for (Student s1 : allStudents){
			if (s1.auth(id, password)){
				currentStudent = s1;
				refresh();
				return true;
			}
		}
		return false;
	}
	public void logout(){
		currentStudent = null;
		refresh();
	}
	public boolean add(String id, String password){
		Student s1 = new Student(id, password);
		return this.add(s1);
		
	}
	
	private boolean add(Student s1) {
		if(allStudents.contains(s1))
			return false;
		else 
			allStudents.add(s1);
		return true;
	}
	
	public String getCurrent() {
		if (currentStudent == null)
			return null;
		else 
			return currentStudent.getId();
	
	}
	
	public boolean currentStudentHasEnoughTime(int s){
		if (currentStudent!=null)
			return maxPlayTime - currentStudent.getSeconds() >= s;
		else
			return false;	
	}

	public boolean currentStudentHasAPlay() {
		if (currentStudent!=null)
			return maxPlaysPerDay > currentStudent.getNumOfPlays();
		else
			return false;
			
		
	}

	public void playedSong(int length) {
		if (currentStudent !=null)
			currentStudent.playASong(length);
	}
	
	public int currentStudentRemainingPlays(){
		if(currentStudent!=null)
			return maxPlaysPerDay - currentStudent.getNumOfPlays();
		else
			return 0;
	}
	
	public int currentStudentRemainingTime(){
		if (currentStudent!=null)
			return maxPlayTime - currentStudent.getSeconds();
		else
			return 0;
	}
	
	public void reset(){
		for (Student s1: allStudents){
			s1.reset();
		}
	}
	
	public String getCurrentStudentId(){
		if (currentStudent != null){
			return currentStudent.getId();
		} else {
			return "Login Please";
		}
	}

	public void refresh() {
		if (getCurrentStudentId().equals("Login Please")){
			currentUserLabel.setText("Please log in");
			currentUserPlaysRemaining.setText("");
			currentUserTimeRemaining.setText("");
		} else {
			currentUserLabel.setText("Name: "+currentStudent.getId());
			currentUserPlaysRemaining.setText("Time Remaining: "+(maxPlayTime-currentStudent.getSeconds()));//+ currentStudent.getSeconds());
			currentUserTimeRemaining.setText("Plays Remaining: "+ (maxPlaysPerDay - currentStudent.getNumOfPlays()));
		}
		this.updateUI();
	}
	
	
}
