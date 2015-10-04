package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends JFrame{
	private class UserPanel extends JPanel {
		private JLabel currentUserName;
		private JLabel currentUserRemainingPlays;
		private JLabel currentUserRemainingTime;

		public UserPanel() {
			this.setLayout(new GridLayout(3, 2));
			this.setSize(100, 100);
			currentUserName = new JLabel("Current User: " + jb.currentStudent());
			currentUserRemainingPlays = new JLabel("Remaining Plays: " + jb.currentStudentRemainingPlays());
			currentUserRemainingTime = new JLabel("Remaining Time: " + jb.currentStudentRemainingTime());
		}

		public UserPanel(String name, int plays, int time) {

			currentUserName = new JLabel("Curent User: " + name);
			currentUserRemainingPlays = new JLabel("Remaining Plays: " + plays);
			currentUserRemainingTime = new JLabel("Remaining Time: " + time);
			this.add(currentUserName);
			this.add(currentUserRemainingPlays);
			this.add(currentUserRemainingTime);

		}

	}
}
