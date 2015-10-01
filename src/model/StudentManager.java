package model;

import java.util.ArrayList;

public class StudentManager {
	private ArrayList<Student> allStudents;
	private Student currentStudent;
	private int maxPlayTime;
	private int maxPlaysPerDay;
	
	public StudentManager(ArrayList<Student> a){
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
	}
	
	public boolean login(String id, String password){
		currentStudent = null;
		for (Student s1 : allStudents){
			if (s1.auth(id, password)){
				currentStudent = s1;
				return true;
			}
		}
		return false;
	}
	public void logout(){
		
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
	
	public int getSize(){
		return allStudents.size();
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
			return -1;
	}
	
	public int currentStudentRemainingTime(){
		if (currentStudent!=null)
			return maxPlayTime - currentStudent.getSeconds();
		else
			return -1;
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

}
