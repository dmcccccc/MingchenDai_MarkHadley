package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Comparable, Serializable {
	private String id;
	private String password;
	private int seconds;
	private int songsPlayed;
	
	public Student(String iniId, String iniPassword) {
		id = iniId;
		password = iniPassword;
		seconds = 0;
		songsPlayed = 0;
	}
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeObject(id);
		out.writeObject(password);
		out.writeInt(seconds);
		out.writeInt(songsPlayed);
		
	}
	
	 @SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		 id = (String) in.readObject();
		 password = (String) in.readObject();
		 seconds = in.readInt();
		 songsPlayed = in.readInt();
	 }
	//increments songsPlayed and total seconds played by the time of the song.
	public void playASong(int length) {
		songsPlayed++;
		seconds += length;
	}
	
	//sets songs played to zero
	public void reset(){
		songsPlayed=0;
	}

	public int getSeconds(){
		return seconds;
	}
	public int getNumOfPlays() {
		return songsPlayed; 
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String toString(){
		return id;
	}

	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}
	
	public boolean auth(String i, String p){
		return (i.equals(this.id) && p.equals(this.password));
	}
	

	
}
