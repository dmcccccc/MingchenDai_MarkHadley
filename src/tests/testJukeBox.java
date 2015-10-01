package tests;
//Author Mingchen Dai, Mark Hadley

// test methods for the model package
import static org.junit.Assert.*;

import org.junit.Test;

import model.JukeBox;
import model.PlayList;
import model.Song;
import model.Student;

public class testJukeBox {

	@Test
	public void testStudent(){
		Song s1 = new Song("spacemusic.au", 6, "microsoft", "spacemusic.au");
		Song s2 = new Song("BlueRidgeMountainMist", 38, "a","BlueRidgeMountainMist.mp3");
		Song s3 = new Song("DeterminedTumbao", 1500*60-5, "b", "DeterminedTumbao.mp3");
		Student s = new Student("Chris", 1);
		assertEquals(0,s.getNumOfPlay());
		assertEquals(0,s.getSeconds());
		s.playASong(s1);
		assertEquals(1,s.getNumOfPlay());
		assertEquals(6,s.getSeconds());
		s.playASong(s1);
		assertEquals(2,s.getNumOfPlay());
		assertEquals(12,s.getSeconds());
		s.playASong(s2);
		assertTrue(!s.canPlay(s3));
		assertEquals(3,s.getNumOfPlay());
		s.playASong(s2);
		assertTrue(!s.canPlay(s2));
		assertEquals("Chris",s.getId());
		s.reset();
		assertTrue(s.canPlay(s2));
		assertEquals(0,s.getNumOfPlay());
		assertEquals(88,s.getSeconds());
		assertEquals(1,s.getPassword());
	}
	
	@Test
	public void TestSong(){
		Song s1 = new Song("spacemusic.au", 6, "microsoft", "spacemusic.au");
		assertTrue(s1.canBePlayed());
		assertEquals("microsoft",s1.getArtist());
		assertEquals("spacemusic.au",s1.getPath());
		assertEquals("spacemusic.au",s1.getName());
		assertEquals(6,s1.getLength());
		s1.play();
		assertTrue(s1.canBePlayed());
		s1.play();
		assertTrue(s1.canBePlayed());
		s1.play();
		assertTrue(!s1.canBePlayed());
		assertEquals(3,s1.getTimesPlayed());
		s1.reset();
		assertEquals(0,s1.getTimesPlayed());
		assertTrue(s1.canBePlayed());
		
		
	}
	
	@Test
	public void TestPlayList(){
		PlayList pl = new PlayList();
		pl.playNextSong();
		Song s1 = new Song("spacemusic.au", 6, "microsoft", "spacemusic.au");
		pl.addSong(s1);
		assertEquals(1, pl.getSize());
	}
	
	@Test
	public void TestJukeBox(){
		JukeBox jb = new JukeBox();
		Song s1 = jb.getSong("Tada");
		assertTrue(s1.canBePlayed());
		try{
			jb.addSong("Devon", 22, s1);
		} catch (Exception e) {
			
		}
		assertEquals(1, jb.getSong("Tada").getTimesPlayed());
		jb.reset();
		assertEquals(0, jb.getSong("Tada").getTimesPlayed());
		
		
		
	}
	
}
