package songplayer;
//Author Mingchen Dai, Mark Hadley

// Main method that queues and plays 3 songs without overlap.

import java.util.ArrayList;

import model.JukeBox;
import model.PlayList;
import model.Song;
import model.Student;

public class QueueAndPlay3SongsInOrder {
	public static PlayList pl;
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public static void main(String[] args) {
		JukeBox jb = new JukeBox();
		Song s1 = new Song("flute.aif", 5, "a", baseDir + "flute.aif");
		Song s2 = new Song("spacemusic.au", 6, "microsoft", baseDir + "spacemusic.au");
		Song s3 = new Song("tada.wav", 2, "microsoft", baseDir + "tada.wav");
		// add songs
		try {
			jb.addSong("Devon", 22, s1);
		} catch (Exception e) {
			System.out.print("song cannot be played");
		}

		try {
			jb.addSong("Devon", 22, s2);
		} catch (Exception e) {
			System.out.print("song cannot be played");
		}

		try {
			jb.addSong("Devon", 22, s3);
		} catch (Exception e) {
			System.out.print("song cannot be played");
		}

		// initiate playing songs
		jb.playList.playNextSong();

	}

}
