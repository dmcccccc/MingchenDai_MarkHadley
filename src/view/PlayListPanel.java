package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class PlayListPanel extends JPanel {
	private JTable jTable;
	
	public PlayListPanel(String[][] data, String[] columnNames){
		//Object[][] data = {{"Mark"},{"Kasandra"},{"Pickles"}};
		//String[] columnNames = {"Name"};
				
		this.setLayout(new BorderLayout());
		this.setSize(200,200);
		this.setLocation(10,10);
		jTable = new JTable(data, columnNames);
		jTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(jTable);
		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(new JLabel("Currently Playing"), BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void redrawPlaylist(){
		jTable.repaint();
	}

	public JTable getTable() {
		return jTable;
	}
}
