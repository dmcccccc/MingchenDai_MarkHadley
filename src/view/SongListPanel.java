package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Song;

public class SongListPanel extends JPanel {
	JTable songTable;
	TableModel model;
	public SongListPanel(TableModel songsTableModel) {
		model = songsTableModel;
		this.setLayout(new BorderLayout());
		songTable = new JTable(model);
		songTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(songTable);
		
		
		int xSize = 300;
		int ySize = 200;
		int xLoc = 425;
		int yLoc = 10;
				
		this.setSize(xSize, ySize);
		this.setLocation(xLoc, yLoc);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(new JLabel("Available Songs"), BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	public String getSelectedSongName(){
		 return (String) songTable.getValueAt(songTable.getSelectedRow(), 1);
		//return (String) model.getValueAt(songTable.getSelectedRow(), 1);
		
	}
	

}
