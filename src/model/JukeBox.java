package model;
//Author Mingchen Dai, Mark Hadley

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.table.TableModel;

// class that holds the PlayList class and ArrayList<Student> and ArrayList<Song> (which is all available songs)
// and verifies if songs can be added to the PlayList due to certain criteria.
public class JukeBox {

	public static String filename = "data";
	public PlayList playList;
	static LocalDate lastPlay;
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	private StudentManager studentM;
	private SongManager songM;

	// boolean loadFromFile: if true, load previous data.
	public JukeBox(boolean loadFromFile) {
		if (loadFromFile) {
			try {
				FileInputStream rawBytes = new FileInputStream(filename);
				ObjectInputStream inFile = new ObjectInputStream(rawBytes);
				studentM = (StudentManager) inFile.readObject();
				inFile.close();
				rawBytes.close();

			} catch (Exception e) {
				System.out.println("File not found, loading fresh Jukebox");
				studentM = new StudentManager();

			}

		} else {
			studentM = new StudentManager();
		}

		songM = new SongManager();
		playList = new PlayList();
		lastPlay = LocalDate.now();

		// set up GUI

		// Register listeners

	}

	public void saveStateToFile(String filename) {
		try{ 
			FileOutputStream bytesToDisk = new FileOutputStream(filename);
			ObjectOutputStream outFile= new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(studentM);
			outFile.close();
			bytesToDisk.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}

	public boolean playSong(){
		int lengthOfSong = songM.getSelectedSongLength();

		if (!studentM.currentStudentHasAPlay()){
			System.out.print("Student has no plays left today");
			return false;
		} else if (!studentM.currentStudentHasEnoughTime(lengthOfSong)){
			System.out.print("Student doesnt have enough time left to play song");
			return false;
		} else if (!songM.selectedSongCanPlay()){
			System.out.println("Selected song cannot be played again today");
			return false;
		}
		playList.addSong(songM.getSelectedSong());
		songM.playSelectedSong();
		studentM.playedSong(lengthOfSong);
		//playList.playNextSong();
		return true;		
	}

	public boolean selectSong(String name){
		return songM.selectSong(name);
	}
	public String currentStudent() {
		return studentM.getCurrent();
	}

	public boolean login(String id, String password) {
		return studentM.login(id, password);
	}
	
	public String getSelectedSongName(){
		return songM.getSelectedSongName();
	}

	public int getSelectedSongTimesPlayed(){
		return songM.getSelectedSongTimesPlayed();
	}
	
	public Song getSelectedSong(){
		return songM.getSelectedSong();
	}
	public void logout() {
		studentM.logout();
	}

	// sets all songs to zero times played by the Jukebox and all songs played
	// by each student to zero.
	public void reset() {
		studentM.reset();
		songM.reset();
	}
	
	public int currentStudentRemainingPlays(){
		return studentM.currentStudentRemainingPlays();
	}
	
	public int currentStudentRemainingTime(){
		return studentM.currentStudentRemainingTime();
	}
	
	public TableModel getSongTableModel(){
		return songM.getTableModel();
	}
	
	public TableModel getPlaylistTableModel(){
		return playList;
	}

	// checks if midnight has occurred (therefore a reset), verifies the
	// student's password, verifies if the student can
	// play the song, and verifies if the song can be played. If those pass, it
	// adds it to the playlist and updates the
	// last time a song was played (LocalDate lastPlay).
	// public void addSong(String refId, int refPassword, Song refSong) throws
	// Exception {
	//
	// // check midnight
	// if (isMidnight()) {
	// reset();
	// }
	// Student refStudent = null;
	// for (int i = 0; i < students.size(); i++) {
	// if (refId.equals(students.get(i).getId()) && refPassword ==
	// students.get(i).getPassword()) {
	// refStudent = students.get(i);
	// }
	// }
	// if (refStudent != null) {
	// if (refStudent.canPlay(refSong) && refSong.canBePlayed()) {
	// playList.addSong(refSong); // add song
	// refStudent.playASong(refSong); // student counter++
	// refSong.play(); // song counter++
	// lastPlay.now(); // record time of last play to check reset
	// }
	// }
	// }

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

	public GraphicsConfiguration getPlayListTableModel() {
		// TODO Auto-generated method stub
		return null;
	}

	// public Song getSong(String name){
	// for (int i = 0; i < songs.size(); i++){
	// if (name.equals(songs.get(i).getName()))
	// return songs.get(i);
	//
	// }
	// return null;
	// }
	// private class AddSongToPlayListActionListener implements ActionListener{
	// @Override
	// public void actionPerformed(ActionEvent arg0) {
	// int length = songM.getSelectedSongLength();
	//
	// if(studentM.currentStudentHasAPlay() &&
	// studentM.currentStudentHasEnoughTime(length) &&
	// songM.selectedSongCanPlay()){
	// studentM.playedSong(length);
	// songM.playSelectedSong();
	// playList.addSong(songM.getSelectedSong());
	// }
	//
	// }
	// }

}
