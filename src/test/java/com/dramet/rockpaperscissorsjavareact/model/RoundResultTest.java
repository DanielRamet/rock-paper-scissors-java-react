package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

public class RoundResultTest {

	private RoundResult victim;
	
	@BeforeEach
	public void setup() {
		victim = new RoundResult();
	}
	
	@Test
	public void roundResult_test() {
		assertNull(victim.getPlayer1Choice());
		assertNull(victim.getPlayer2Choice());
		assertNull(victim.getResult());
		
		String label1 = RandomString.make();
		String label2 = RandomString.make();
		String label3 = RandomString.make();
		
		victim.setPlayer1Choice(label1);
		victim.setPlayer2Choice(label2);
		victim.setResult(label3);
		
		assertNotNull(victim.getPlayer1Choice());
		assertNotNull(victim.getPlayer2Choice());
		assertNotNull(victim.getResult());
		
		assertEquals(victim.getPlayer1Choice(), label1);
		assertEquals(victim.getPlayer2Choice(), label2);
		assertEquals(victim.getResult(), label3);
	}
}
