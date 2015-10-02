package model;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongManager {
	private ArrayList<Song> allSongs;
	private int maxPlayPerDay;
	private Song currentSong;
	private static int MAX_SONGS_PER_DAY = 3;
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public SongManager() {
		maxPlayPerDay = MAX_SONGS_PER_DAY;
		currentSong = null;
		allSongs = new ArrayList<Song>();
		allSongs.add(new Song("Space Music", 6, "Unknown", baseDir + "spacemusic.au"));
		allSongs.add(
				new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", baseDir + "BlueRidgeMountainMist.mp3"));
		allSongs.add(new Song("Determined Tumbao", 20, "FreePlay Music", baseDir + "DeterminedTumbao.mp3"));
		allSongs.add(new Song("Tada", 2, "Microsoft", baseDir + "tada.wav"));
		allSongs.add(new Song("Untameable Fire", 282, "Pierre Langer", baseDir + "UntamableFire.mp3"));
		allSongs.add(new Song("Swing Cheese", 15, "FreePlay Music", baseDir + "UntamableFire.mp3"));
		allSongs.add(new Song("Flute", 5, "Sun Microsystems", baseDir + "flute.aif"));
	}

	public SongManager(ArrayList<Song> al) throws IOException {
		maxPlayPerDay = MAX_SONGS_PER_DAY;
		currentSong = null;
		allSongs = al;
	}

	public void add(Song s1) {
		allSongs.add(s1);
	}

	public boolean selectSong(String name) {
		for (Song s1 : allSongs) {
			if (name.equals(s1.getName())) {
				currentSong = s1;
				return true;
			}
		}
		return false;
	}

	public int size() {
		return allSongs.size();
	}

	public String getSelectedSongName() {
		if (currentSong != null) {
			return currentSong.getName();
		}
		return null;
	}

	public boolean selectedSongCanPlay() {
		return currentSong.getTimesPlayed() < maxPlayPerDay;
	}

	public Song playSelectedSong() {
		currentSong.play();
		return currentSong;
	}

	public void reset() {
		for (Song s1 : allSongs) {
			s1.reset();
		}
	}
	
	public int getSelectedSongLength(){
		return currentSong.getLength();
	}
	
	public TableModel getTableModel(){
		return new SongListTableModel();
	
	}
	
	public Song getSelectedSong(){
		return currentSong;
	}

	
	private class SongListTableModel implements TableModel {
		private ArrayList<Song> data = new ArrayList<Song>();

		public SongListTableModel() {
			data = allSongs;
		}

		public Song getSong(String name) {
			for (int i = 0; i <= data.size(); i++) {
				if (data.get(i).getName().equals(name))
					return data.get(i);
			}
			return null;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0)
				return "Artist";
			if (columnIndex == 1)
				return "Title";
			return "Seconds";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 2)
				return int.class;
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Song currentSong = data.get(rowIndex);
			if (columnIndex == 0)
				return currentSong.getArtist();
			if (columnIndex == 1)
				return currentSong.getName();
			return currentSong.getLength();
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

	}
}
