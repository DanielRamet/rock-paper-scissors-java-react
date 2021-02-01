package com.dramet.rockpaperscissorsjavareact.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.factory.GameItemFactory;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;
import com.dramet.rockpaperscissorsjavareact.service.impl.GameServiceImpl;

@SpringBootTest
public class GameServiceImplTest {

	private GameServiceImpl victim;

	@BeforeEach
	public void setup() {
		victim = new GameServiceImpl();
	}
	
	@Test
	public void getAllItemsTest() {
		assertEquals(GameItemFactory.getGameItems(), victim.getAllItems());
	}
	
	@Test
	public void getItemTest() {
		try {
			victim.getItem(null);
		}catch(GameItemNotFoundException e) {
			assertTrue(true);
		}
		
		try {
			victim.getItem("Invalid");
		}catch(GameItemNotFoundException e) {
			assertTrue(true);
		}		
		
		try {
			final GameItem item = victim.getItem(EGameValue.PAPER.name());
			assertNotNull(item);
			assertEquals(item.getValue(), EGameValue.PAPER);
		} catch (GameItemNotFoundException e) {
			assertTrue(false);
		}
		
		try {
			final GameItem item = victim.getItem(EGameValue.SCISSORS.name());
			assertNotNull(item);
			assertEquals(item.getValue(), EGameValue.SCISSORS);
		} catch (GameItemNotFoundException e) {
			assertTrue(false);
		}
		
		try {
			final GameItem item = victim.getItem(EGameValue.ROCK.name());
			assertNotNull(item);
			assertEquals(item.getValue(), EGameValue.ROCK);
		} catch (GameItemNotFoundException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void playRoundTest() throws GameItemNotFoundException {
		String expectedLabel1 = "Player 2 Wins"; 
		String expectedLabel2 = "Player 1 Wins";
		String expectedLabel3 = "It is a Draw";
		GameItem item1 = victim.getItem(EGameValue.ROCK.name());
		GameItem item2 = victim.getItem(EGameValue.PAPER.name());
		
		RoundResult result = victim.playRound(item1, item2);
		assertNotNull(result);
		assertEquals(result.getPlayer1Choice(), item1.getLabel());
		assertEquals(result.getPlayer2Choice(), item2.getLabel());
		assertEquals(result.getResult(), expectedLabel1);
		
		result = victim.playRound(item2, item1);
		assertNotNull(result);
		assertEquals(result.getPlayer1Choice(), item2.getLabel());
		assertEquals(result.getPlayer2Choice(), item1.getLabel());
		assertEquals(result.getResult(), expectedLabel2);
		
		result = victim.playRound(item1, item1);
		assertNotNull(result);
		assertEquals(result.getPlayer1Choice(), item1.getLabel());
		assertEquals(result.getPlayer2Choice(), item1.getLabel());
		assertEquals(result.getResult(), expectedLabel3);
		
		result = victim.playRound(item2, item2);
		assertNotNull(result);
		assertEquals(result.getPlayer1Choice(), item2.getLabel());
		assertEquals(result.getPlayer2Choice(), item2.getLabel());
		assertEquals(result.getResult(), expectedLabel3);
	}
	
	@Test
	public void getGameResultTest() throws GameItemNotFoundException {
		Game results = victim.getTotalResults();
		assertNotNull(results);
		assertEquals(results.getTotalRoundsPlayed(), 0);
		assertEquals(results.getTotalDraws(), 0);
		assertEquals(results.getTotalWinsPlayer1(), 0);
		assertEquals(results.getTotalWinsPlayer2(), 0);
		
		GameItem item1 = victim.getItem(EGameValue.ROCK.name());
		GameItem item2 = victim.getItem(EGameValue.PAPER.name());
		GameItem item3 = victim.getItem(EGameValue.SCISSORS.name());
		
		victim.playRound(item1, item2);
		victim.playRound(item2, item3);
		victim.playRound(item3, item1);
		victim.playRound(item1, item1);
		victim.playRound(item2, item2);
		victim.playRound(item3, item3);
		
		results = victim.getTotalResults();
		assertNotNull(results);
		assertEquals(results.getTotalRoundsPlayed(), 6);
		assertEquals(results.getTotalDraws(), 3);
		assertEquals(results.getTotalWinsPlayer1(), 0);
		assertEquals(results.getTotalWinsPlayer2(), 3);
	}
}
