package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

public class GameItemTest {
	
	@Test
	public void gameItemCreateTestProperties() {
		String label = RandomString.make(10);
		EGameValue value = EGameValue.values()[0];
		GameItem item = new GameItem(label, value);
		assertNotNull(item);
		assertEquals(item.getLabel(), label);
		assertEquals(item.getValue(), value);
	}
	
	@Test
	public void gameItemEqualsTest() {
		GameItem item1 = new GameItem(RandomString.make(5), EGameValue.ROCK);
		GameItem item2 = new GameItem(RandomString.make(5), EGameValue.ROCK);
		GameItem item3 = new GameItem(RandomString.make(5), EGameValue.PAPER);
		
		assertTrue(item1.equals(item2));
		assertFalse(item1.equals(item3));
		assertFalse(item1.equals(null));
	}
}
