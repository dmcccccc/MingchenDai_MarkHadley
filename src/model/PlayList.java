package model;

//Author Mingchen Dai, Mark Hadley
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// class that holds songs to be played in order by the JukeBox class.
public class PlayList {
	private ArrayList<Song> songList = new ArrayList<Song>();
	public EndOfSongListener listener = new OurEndOfSongListener();
	private int index;
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public PlayList() {
		index = 0;
	}

	// adds a song to be played.
	public void addSong(Song s) {
		songList.add(s);
	}

	// plays next song and increments the index
	public void playNextSong() {
		if (index < songList.size()) {
			SongPlayer.playFile(listener, songList.get(index).getPath());
			index++;
		}

	}
	
	// private EndoOfSongListener, plays next song in playlist.
	private class OurEndOfSongListener implements EndOfSongListener {
		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
			playNextSong();
		}
	}

	public int getSize() {
		return songList.size();
	}

}
