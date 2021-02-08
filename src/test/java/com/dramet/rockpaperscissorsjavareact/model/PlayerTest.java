package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

public class PlayerTest {

	private Player player;
	
	@BeforeEach
	public void setup() {
		this.player = new Player(RandomString.make());
	}
	
	@Test
	public void playerCreateTest() {
		assertNotNull(player);
		assertNotNull(player.getName());
		assertTrue(player.getName().length() > 0);
		assertNull(player.getMove());
	}
	
	@Test
	public void playerSetMoveTest() {
		GameItem item = new GameItem(RandomString.make(5), EGameValue.ROCK);
		player.setMove(item);
		
		assertNotNull(player.getMove());
		assertEquals(player.getMove(), item);
	}
	
	@Test
	public void playAgainstTest() {
		final Player player2 = new Player(RandomString.make());
		
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.ROCK);
		
		//Case 1: both moves Null
		EPlayResult result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
		
		//Case 2: both equals
		player.setMove(item1);
		player2.setMove(item1);
		result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
		
		//Case 3: player1 wins
		player.setMove(item1);
		player2.setMove(item2);
		result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.WIN);
		
		result = player2.playAgainst(player);
		assertEquals(result, EPlayResult.LOSE);
		
		player.setMove(null);
		player2.setMove(item1);
		result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.LOSE);
		
		player.setMove(item1);
		player2.setMove(null);
		result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.WIN);
		
		player.setMove(null);
		player2.setMove(null);
		result = player.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
	}
}
