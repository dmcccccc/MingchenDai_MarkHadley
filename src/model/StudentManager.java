package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentManager implements Serializable {
	private ArrayList<Student> allStudents;
	private Student currentStudent;
	private int maxPlayTime;
	private int maxPlaysPerDay;
	
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
