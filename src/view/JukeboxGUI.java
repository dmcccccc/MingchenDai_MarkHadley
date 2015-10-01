package view;
//Author Mingchen Dai, Mark Hadley

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.glass.events.MouseEvent;

import model.JukeBox;
import model.PlayList;
import model.Song;
import model.StudentManager;

public class JukeboxGUI extends JFrame{
	
	private static JukeBox jb;
	public static void main(String[] args) {
		jb = new JukeBox();
	    new JukeboxGUI().setVisible(true);
	  }
	
	private songList songs;
	private PlayList playList;
	private JTextField idIn;
	private JTextField passwordIn;
	private JButton login;
	private JButton logout;
	private JTextField currentUser;
	
	public JukeboxGUI() {
	    setSize(600, 450);
	    setLocation(300, 80);
	    this.setTitle("JukeBox");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    songs = new songList();
	    JTable view = new JTable(songs);
	    view.setModel(songs);
	    

	    // Can use a layout strategy or null.  If null, every component must have it's
	    // location and size set. Also, to see column headers, you must decorate the
	    // JTable with a JScollPane. New stuff, but taught in the most efficient manner.
	    setLayout(null);
	    JScrollPane scrollPane = new JScrollPane(view); // Put a scrollpane around table 
	    scrollPane.setLocation(25, 25);
	    scrollPane.setSize(300, 200);
	    this.add(scrollPane); // The scrollpane holds the JTable inside as an instance var
	    
	    // ID
	    JLabel id = new JLabel("ID");
	    id.setLocation(25, 250);
	    id.setSize(25, 25);
	    this.add(id);
	    
	    idIn = new JTextField("");
	    idIn.setLocation(45, 250);
	    idIn.setSize(100, 25);
	    this.add(idIn);
	    
	    // PW
	    JLabel password = new JLabel("Password");
	    password.setLocation(160, 250);
	    password.setSize(60, 25);
	    this.add(password);
	    
	    passwordIn = new JTextField("");
	    passwordIn.setLocation(225, 250);
	    passwordIn.setSize(100, 25);
	    this.add(passwordIn);
	    
	    // Login Button
	    login = new JButton("Login");
	    login.setLocation(25, 300);
	    login.setSize(140, 50);
	    login.addActionListener(new LoginListener());
	    this.add(login);
	    
	    // Logout Button
	    logout = new JButton("Logout");
	    logout.setLocation(185, 300);
	    logout.setSize(140, 50);
	    logout.addActionListener(new LogoutListener());
	    this.add(logout);
	    
	    JLabel info = new JLabel("Current User");
	    info.setLocation(25, 375);
	    info.setSize(100, 25);
	    this.add(info);
	    
	    // current user
	    currentUser = new JTextField("Login please");
	    currentUser.setSize(200, 25);
	    currentUser.setLocation(125, 375);
	    currentUser.setEditable(false);
	    this.add(currentUser);
	  }

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String loginId = idIn.getText();
			String loginPassword = passwordIn.getText();
			if (jb.login(loginId, loginPassword)) {
				currentUser.setText(loginId);
				idIn.setText("");
				passwordIn.setText("");
			}
			else {
				idIn.setText("");
				passwordIn.setText("");
				JOptionPane.showMessageDialog(null, "Invalid Login");
			}
		}
    	
    }
	
	private class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jb.logout();
			currentUser = new JTextField("Login please");
		}
		
	}
}

class songList implements TableModel {
	  private ArrayList<Song> data = new ArrayList<Song>();
	  private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	  public static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			      + System.getProperty("file.separator");
	  
	  // change to manager
	  public songList() {  
	    data.add(new Song("Space Music", 6, "Unknown", baseDir+"spacemusic.au"));
	    data.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett", baseDir+"BlueRidgeMountainMist.mp3"));
	    data.add(new Song("Determined Tumbao", 20, "FreePlay Music",  baseDir+"DeterminedTumbao.mp3"));
	    data.add(new Song("Tada", 2, "Microsoft", baseDir + "tada.wav"));
	    data.add(new Song("Untameable Fire", 282, "Pierre Langer", baseDir + "UntamableFire.mp3"));
	    data.add(new Song("Swing Cheese", 15, "FreePlay Music", baseDir + "UntamableFire.mp3"));
	    data.add(new Song("Flute", 5, "Sun Microsystems", baseDir + "flute.aif"));
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

    