package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.bytebuddy.utility.RandomString;

@SpringBootTest
public class PlayerTest {

	@Test
	public void playerCreateTest() {
		String name = RandomString.make(10);
		final Player player = new Player(name);
		
		assertNotNull(player);
		assertEquals(player.getName(), name);
		assertNull(player.getMove());
	}
	
	@Test
	public void playerSetMoveTest() {
		final Player player = new Player(RandomString.make());
		GameItem item = new GameItem(RandomString.make(5), EGameValue.ROCK);
		player.setMove(item);
		
		assertNotNull(player.getMove());
		assertEquals(player.getMove(), item);
	}
	
	@Test
	public void playAgainstTest() {
		final Player player1 = new Player(RandomString.make());
		final Player player2 = new Player(RandomString.make());
		
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.ROCK);
		
		//Case 1: both moves Null
		EPlayResult result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
		
		//Case 2: both equals
		player1.setMove(item1);
		player2.setMove(item1);
		result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
		
		//Case 3: player1 wins
		player1.setMove(item1);
		player2.setMove(item2);
		result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.WIN);
		
		result = player2.playAgainst(player1);
		assertEquals(result, EPlayResult.LOSE);
		
		player1.setMove(null);
		player2.setMove(item1);
		result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.LOSE);
		
		player1.setMove(item1);
		player2.setMove(null);
		result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.WIN);
		
		player1.setMove(null);
		player2.setMove(null);
		result = player1.playAgainst(player2);
		assertEquals(result, EPlayResult.DRAW);
	}
}
