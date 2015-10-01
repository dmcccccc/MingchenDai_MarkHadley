package model;
//Author Mingchen Dai, Mark Hadley

// Class that holds many attributes associated with a song to play in the JukeBox class.
public class Song {
	private String name;
	private int seconds;
	private String artist;
	private int timesPlayed;
	private String path;

	// constructor
	public Song(String iniName, int iniSeconds, String iniArtist, String inipath) {
		name = iniName;
		seconds = iniSeconds;
		artist = iniArtist;
		timesPlayed = 0;
		path = inipath;
	}

	// returns if the song can be played. Songs cannot be played more than 3
	// times in a day.
	public boolean canBePlayed() {
		return timesPlayed < 3;
	}

	// increments the timesPlayed counter
	public void play() {
		timesPlayed++;
	}

	// reset play times at midnight, called by JukeBox or PlayList
	public void reset() {
		timesPlayed = 0;
	}

	public String getPath() {
		return path;
	}

	public int getLength() {
		return seconds;
	}

	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}
}
