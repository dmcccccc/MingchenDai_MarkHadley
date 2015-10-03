package model;

//Author Mingchen Dai, Mark Hadley
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// class that holds songs to be played in order by the JukeBox class.
public class PlayList implements TableModel {

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

		if (songList.size() <= 1)
			this.playNextSong();
	}

	// plays next song and increments the index
	public void playNextSong() {
		if (songList.size() > 0)
			SongPlayer.playFile(listener, songList.get(0).getPath());
	}

	// private EndoOfSongListener, plays next song in playlist.
	private class OurEndOfSongListener implements EndOfSongListener {
		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
			System.out.println("Inside playList class, plaing a song");
			System.out.println("removing song that just played");
			for (Song s : songList) {
				System.out.println(s.getName());
			}
			songList.remove(0);

			// System.out.println("decrementing index");
			// we need to get rid of index.
			// index--;
			playNextSong();
		}
	}

	public int getSize() {
		return songList.size();
	}

	public void add(String name) {

	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return "Current Song";
	}

	@Override
	public int getRowCount() {
		return getSize();
	}

	@Override
	public String getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub

		return songList.get(arg0).getName();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}
