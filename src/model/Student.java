package model;

public class Student {
	private String id;
	private int password;
	private int seconds;
	private int songsPlayed;
	
	public Student(String iniId, int iniPassword) {
		id = iniId;
		password = iniPassword;
		seconds = 0;
		songsPlayed = 0;
	}

	// Just check whether student can play, if song can be played will be checked in Song.java; both called by JukeBox or PlayList
	public boolean canPlay(Song refSong) {
		if (songsPlayed < 3 && seconds + refSong.getLength() <= 90000) {
			return true;
		}
		else {
			return false;
		}
	}
	//increments songsPlayed and total seconds played by the time of the song.
	public void playASong(Song refSong) {
		songsPlayed++;
		seconds += refSong.getLength();
	}
	
	//sets songs played to zero
	public void reset(){
		songsPlayed=0;
	}

	public int getSeconds(){
		return seconds;
	}
	public int getNumOfPlay() {
		return songsPlayed; 
	}

	public String getId() {
		return id;
	}

	public int getPassword() {
		return password;
	}
}
