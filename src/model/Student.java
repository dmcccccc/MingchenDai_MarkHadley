package model;

public class Student implements Comparable {
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

	// Just check whether student can play, if song can be played will be checked in Song.java; both called by JukeBox or PlayList
//	public boolean canPlay(Song refSong) {
//		if (songsPlayed < 3 && seconds + refSong.getLength() <= 90000) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
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

//	private String getPassword() {
//		return password;
//	}
	
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
