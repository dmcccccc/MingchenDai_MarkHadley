package model;

import java.awt.BorderLayout;
import java.awt.Color;
//Author Mingchen Dai, Mark Hadley
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// class that holds songs to be played in order by the JukeBox class.
public class PlayList extends JPanel implements TableModelListener {

	private ArrayList<Song> songList;
	public TableModel model;
	public JTable table;
	public EndOfSongListener listener;
	private int index;

	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public PlayList() {
		
		listener = new OurEndOfSongListener();
		
		songList = new ArrayList<Song>();
		model = new PlaylistTableModel();
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		setLayout(null);
		scrollPane.setSize(200, 400);
	    
		index = 0;
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));   
		this.setSize(200, 400);
		this.setLocation(10,10);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		table.getModel().addTableModelListener(this);
		
	}

	// adds a song to be played.
	public void addSong(Song s) {
		songList.add(s);
		table.setValueAt(s.getName(), 1, 0);
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
				//songList.remove(0);
				model.removeSong();
				//if (songList.size()>0)
					playNextSong();
				
			}
		}
	
	

	
	private class PlaylistTableModel extends AbstractTableModel{
		private ArrayList<String> songs;
		private String[] columnNames;
		
		public PlaylistTableModel(){
			songs = new ArrayList<String>();
			
			for (Song s : songList){
				songs.add(s.getName());
			}
			columnNames = new String[]{"Playlist songs"};
		}
		
		public void removeSong(){
			//if (songs.size()>0)
			//	songs.remove(0);
			fireTableDataChanged();
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			return "Playlist songs";
		}

		@Override
		public int getRowCount() {
			return songs.size();
			
			
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			if (rowIndex <= songs.size()){
				return songs.get(rowIndex);
			}
			return null;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			songs.add((String)aValue);
			fireTableCellUpdated(rowIndex,columnIndex);
		}
		
		

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("Table changed");
		this.repaint();
	}

}
	
