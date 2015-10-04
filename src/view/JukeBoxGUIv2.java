package view;
//Author Mingchen Dai, Mark Hadley

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.glass.events.MouseEvent;

import model.JukeBox;
import model.PlayList;
import model.Playlist2;
import model.Song;
import model.SongManager;
import model.StudentManager;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;

public class JukeBoxGUIv2 extends JFrame {

	private static JukeBox jb;
	private PlayList playList;
	// private SongList songs;

	private JTextField idIn;
	private JTextField passwordIn;
	private JButton login;
	private JButton logout;
	private JTextField currentUser;
	private StudentManager studentManager;
	private UserPanel userPanel;
	private SongListPanel songListPanel;
	private Playlist2 playlistPanel;
	private JButton addSongButton;

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				boolean openFromFile = false;
				jb = new JukeBox(openFromFile);
				new JukeBoxGUIv2(jb).setVisible(true);
				jb.selectSong("Tada");
				jb.login("Chris", "1");
			}
		});
	}
	public JukeBoxGUIv2(JukeBox jb) {
		setSize(600, 450);
		setLocation(300, 80);
		this.setTitle("JukeBox");
		this.setLayout(new GridLayout(2, 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setupComponents
		setupComponents();
		// layoutGUI
		layoutGui();
		// register Listeners
		registerListeners();

	}

	public void setupComponents() {
		// song list panel
		songListPanel = new SongListPanel(jb.getSongTableModel());
		playlistPanel = jb.getPlaylist();
		studentManager = jb.getStudentManager();
	}

	
	private void layoutGui() {
		this.setLayout(null);
		this.setSize(750, 500);
		// userPanel.setLocation(200,100);
		// this.add(userPanel);
		addSongButton = new JButton("Add Song");
		JPanel addSongButtonPanel = new JPanel();
		addSongButtonPanel.add(addSongButton);
		addSongButtonPanel.setLayout(new FlowLayout());
		addSongButtonPanel.setSize(100, 35);
		addSongButtonPanel.setLocation(250, 10);

		this.add(addSongButtonPanel);
		this.add(songListPanel);
		this.add(playlistPanel);
		this.add(studentManager);
		
		

	}

	private void registerListeners() {
		addSongButton.addActionListener(new AddSongActionListener());

	}

	private class AddSongActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.print(songListPanel.getSelectedSongName());
			jb.selectSong(songListPanel.getSelectedSongName());
			System.out.print(jb.getSelectedSongName());
			jb.playSong();
			studentManager.refresh();
		}
	}

}
