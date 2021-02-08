package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.bytebuddy.utility.RandomString;

public class GameTest {

	private Game game;
	
	@BeforeEach
	public void setup() {
		this.game = new Game();
	}
	
	@Test
	public void createGameTest() {
		assertNotNull(game);
		assertNotNull(game.getPlayer1());
		assertNotNull(game.getPlayer2());
		
		assertEquals(game.getPlayer1().getName(), Player.DEFAULT_PLAYER1_NAME);
		assertEquals(game.getPlayer2().getName(), Player.DEFAULT_PLAYER2_NAME);
		
		assertEquals(game.getTotalDraws(), 0);
		assertEquals(game.getTotalWinsPlayer1(), 0);
		assertEquals(game.getTotalWinsPlayer2(), 0);
	}
	
	@Test
	public void playRound_RoundResult_Test() {
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.ROCK);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item3 = new GameItem(RandomString.make(), EGameValue.SCISSORS);
		
		RoundResult victim = game.playRound(item1, item2);
		assertNotNull(victim);
		assertEquals(victim.getPlayer1Choice(), item1.getLabel());
		assertEquals(victim.getPlayer2Choice(), item2.getLabel());
		assertNotNull(victim.getResult());
		assertTrue(victim.getResult().startsWith(game.getPlayer2().getName()));
		
		victim = game.playRound(item1, item3);
		assertNotNull(victim);
		assertEquals(victim.getPlayer1Choice(), item1.getLabel());
		assertEquals(victim.getPlayer2Choice(), item3.getLabel());
		assertNotNull(victim.getResult());
		assertTrue(victim.getResult().startsWith(game.getPlayer1().getName()));
		
		victim = game.playRound(item3, item3);
		assertNotNull(victim);
		assertEquals(victim.getPlayer1Choice(), item3.getLabel());
		assertEquals(victim.getPlayer2Choice(), item3.getLabel());
		assertNotNull(victim.getResult());
		assertFalse(victim.getResult().startsWith(game.getPlayer2().getName()));
		assertFalse(victim.getResult().startsWith(game.getPlayer1().getName()));
	}
	
	@Test
	public void playRound_TotalDraws_Test() {
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.ROCK);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item3 = new GameItem(RandomString.make(), EGameValue.SCISSORS);
		
		game.playRound(item1, item1);
		game.playRound(item1, item1);
		game.playRound(item2, item1);
		game.playRound(item3, item1);
		game.playRound(item3, item3);
		game.playRound(item2, item2);
		
		assertEquals(game.getTotalDraws(), 4);
	}
	
	@Test
	public void playRound_TotalWinsPlayer1_Test() {
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.ROCK);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item3 = new GameItem(RandomString.make(), EGameValue.SCISSORS);
		
		game.playRound(item1, item1);
		game.playRound(item2, item1);
		game.playRound(item3, item1);
		game.playRound(item3, item2);
		
		assertEquals(game.getTotalWinsPlayer1(), 2);
	}
	
	@Test
	public void playRound_TotalWinsPlayer2_Test() {
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.ROCK);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item3 = new GameItem(RandomString.make(), EGameValue.SCISSORS);
		
		game.playRound(item1, item1);
		game.playRound(item2, item1);
		game.playRound(item3, item1);
		
		assertEquals(game.getTotalWinsPlayer2(), 1);
	}
	
	@Test
	public void playRound_TotalRounds_Test() {
		final GameItem item1 = new GameItem(RandomString.make(), EGameValue.ROCK);
		final GameItem item2 = new GameItem(RandomString.make(), EGameValue.PAPER);
		final GameItem item3 = new GameItem(RandomString.make(), EGameValue.SCISSORS);
		
		game.playRound(item1, item1);
		game.playRound(item2, item1);
		game.playRound(item3, item1);
		
		assertEquals(game.getTotalRoundsPlayed(), 3);
	}
}
