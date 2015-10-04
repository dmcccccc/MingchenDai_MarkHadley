package model;

import java.awt.BorderLayout;
import java.awt.Color;
//Author Mingchen Dai, Mark Hadley
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// class that holds songs to be played in order by the JukeBox class.
public class Playlist2 extends JPanel{

	private ArrayList<Song> songList;
	public DefaultListModel model;
	public JList list;
	public EndOfSongListener listener;
	public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	public Playlist2() {
		this.setLayout(new BorderLayout());
		songList = new ArrayList<Song>();
		listener = new OurEndOfSongListener();
		model = new DefaultListModel();
		list = new JList(model);
		this.add(list);
		this.setSize(100,100);
		this.add(new JLabel("Currently Playing"),BorderLayout.NORTH);
	}

	
	// adds a song to be played.
	public void addSong(Song s) {
		model.addElement((String)s.getName());
		songList.add(s);
		if (model.size() <= 1)
			this.playNextSong();
	}

	// plays next song and increments the index
	public void playNextSong() {
		if (!songList.isEmpty()) {
			SongPlayer.playFile(listener, songList.get(0).getPath());
			
		}
	}

	// private EndoOfSongListener, plays next song in playlist.
		private class OurEndOfSongListener implements EndOfSongListener {
			@Override
			public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
				//songList.remove(0);
				model.remove(0);
				songList.remove(0);
				playNextSong();
				
			}
		}

		

}
	
