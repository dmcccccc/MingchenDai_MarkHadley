package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.JukeBox;
import model.PlayList;
import model.Song;
import model.SongManager;
import model.Student;
import model.StudentManager;

public class TestIteration2 {
	private static final int MAX_PLAY_TIME = 90000;
	private static String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
	
	@Test
	public void TestStudentManager(){
		StudentManager sm = new StudentManager();
		
		assertEquals(4, sm.getSize());
		assertTrue(sm.add("Mingchen", "777"));
		assertEquals(5, sm.getSize());
		
		assertTrue(sm.login("Mingchen", "777"));
		assertEquals("Mingchen", sm.getCurrent());
		assertTrue(sm.currentStudentHasEnoughTime(90000));
		assertEquals(90000, sm.currentStudentRemainingTime());
		assertTrue(sm.currentStudentHasAPlay());
		assertEquals(3, sm.currentStudentRemainingPlays());
		sm.playedSong(100);
		assertTrue(sm.currentStudentHasEnoughTime(89900));
		assertEquals(89900, sm.currentStudentRemainingTime());
		assertTrue(sm.currentStudentHasAPlay());
		assertEquals(2, sm.currentStudentRemainingPlays());
		sm.playedSong(89899);
		assertEquals(1, sm.currentStudentRemainingTime());
		assertTrue(sm.currentStudentHasEnoughTime(1));
		assertTrue(sm.currentStudentHasAPlay());
		sm.playedSong(1);
		assertTrue(sm.currentStudentHasEnoughTime(0));
		assertTrue(!sm.currentStudentHasAPlay());
		sm.reset();
		assertTrue(sm.currentStudentHasAPlay());
		assertTrue(sm.currentStudentHasEnoughTime(0));
		sm.login("Mingchen", "777");
//		assertTrue(sm.currentStudentHasEnoughTime(MAX_PLAY_TIME));
//		assertTrue(sm.currentStudentHasAPlay());
//		assertTrue(!sm.login("Mark","888"));
//		assertEquals(null,sm.getCurrentStudentId());
//		assertTrue(!sm.currentStudentHasEnoughTime(-1));
//		assertTrue(sm.login("Mingchen", "777"));
//		assertEquals("Mingchen",sm.getCurrentStudentId());

	}
	
	@Test
	public void TestSongManager(){
		SongManager sm = new SongManager();
		assertEquals(7,sm.size());
		assertEquals(null, sm.getSelectedSongName());
		assertTrue(sm.selectSong("Space Music"));
		assertTrue(!sm.selectSong("Space Name Blah blah"));
		assertEquals("Space Music", sm.getSelectedSongName());
		assertTrue(sm.selectedSongCanPlay());
		sm.playSelectedSong();
		assertTrue(sm.selectedSongCanPlay());
		sm.playSelectedSong();
		assertTrue(sm.selectedSongCanPlay());
		sm.playSelectedSong();
		assertTrue(!sm.selectedSongCanPlay());
		assertTrue(sm.selectSong("Tada"));
		assertTrue(sm.selectedSongCanPlay());		
		assertTrue(sm.selectSong("Space Music"));
		assertTrue(!sm.selectedSongCanPlay());		
		sm.reset();
		assertTrue(sm.selectedSongCanPlay());		
		
	}
	
	@Test
	public void TestPlayList(){
		PlayList pl = new PlayList();
		SongManager sm = new SongManager();
		sm.selectSong("Space Music");
		pl.addSong(sm.getSelectedSong());
		pl.playNextSong();
	}
	
	@Test
	public void TestWriteandReadState(){
		JukeBox jb = new JukeBox(false);
		
		
	}
}
