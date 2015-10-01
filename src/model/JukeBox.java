package model;
//Author Mingchen Dai, Mark Hadley

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// class that holds the PlayList class and ArrayList<Student> and ArrayList<Song> (which is all available songs)
// and verifies if songs can be added to the PlayList due to certain criteria.
public class JukeBox {
	ArrayList<Student> students;
	ArrayList<Song> songs;
	public PlayList playList;
	static LocalDate lastPlay;
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	public JukeBox() {
		songs = new ArrayList<Song>(); // all of the possible songs
		playList = new PlayList();
		lastPlay = LocalDate.now();
		students = new ArrayList<Student>();
		students.add(new Student("Chris", 1));
		students.add(new Student("Devon", 22));
		students.add(new Student("River", 333));
		students.add(new Student("Ryan", 4444));
		songs.add(new Song("Space Music", 6, "Unknown", baseDir+"spacemusic.au"));
		songs.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", baseDir+"BlueRidgeMountainMist.mp3"));
		songs.add(new Song("Determined Tumbao", 20, "FreePlay Music",  baseDir+"DeterminedTumbao.mp3"));
		songs.add(new Song("Tada", 2, "Microsoft", baseDir + "tada.wav"));
		songs.add(new Song("Untameable Fire", 282, "Pierre Langer", baseDir + "UntamableFire.mp3"));
		songs.add(new Song("Swing Cheese", 15 , "FreePlay Music", baseDir + "UntamableFire.mp3"));
		songs.add(new Song("Flute", 5, "Sun Microsystems", baseDir + "flute.aif"));
	}

	// sets all songs to zero times played by the Jukebox and all songs played
	// by each student to zero.
	public void reset() {
		for (int i = 0; i < songs.size(); i++) {
			songs.get(i).reset();
		}
		for (int i = 0; i < students.size(); i++) {
			students.get(i).reset();
		}
	}

	// checks if midnight has occurred (therefore a reset), verifies the
	// student's password, verifies if the student can
	// play the song, and verifies if the song can be played. If those pass, it
	// adds it to the playlist and updates the
	// last time a song was played (LocalDate lastPlay).
	public void addSong(String refId, int refPassword, Song refSong) throws Exception {

		// check midnight
		if (isMidnight()) {
			reset();
		}
		Student refStudent = null;
		for (int i = 0; i < students.size(); i++) {
			if (refId.equals(students.get(i).getId()) && refPassword == students.get(i).getPassword()) {
				refStudent = students.get(i);
			}
		}
		if (refStudent != null) {
			if (refStudent.canPlay(refSong) && refSong.canBePlayed()) {
				playList.addSong(refSong); // add song
				refStudent.playASong(refSong); // student counter++
				refSong.play(); // song counter++
				lastPlay.now(); // record time of last play to check reset
			}
		}
	}

	// private method that sees if midnight has occurred since last time a song
	// was played.
	private boolean isMidnight() {
		LocalDate time = LocalDate.now();
		if (time.isAfter(lastPlay)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Song getSong(String name){
		for (int i = 0; i < songs.size(); i++){
			if (name.equals(songs.get(i).getName()))
				return songs.get(i);
			
		}
		return null;
	}
}
