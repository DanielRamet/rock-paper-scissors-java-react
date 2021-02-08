package com.dramet.rockpaperscissorsjavareact.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.factory.GameItemFactory;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;
import com.dramet.rockpaperscissorsjavareact.service.impl.GameServiceImpl;

import net.bytebuddy.utility.RandomString;

@SpringBootTest
public class GameServiceImplTest {
	
	@TestConfiguration
	static class GameServiceImplConfigurationTest{
		@Bean
		public GameServiceImpl gameService() {
			return new GameServiceImpl();
		}		
	}
	
	@Autowired
	private GameServiceImpl gameService;

	@MockBean
	private Game game;
	@MockBean
	private ReentrantLock lock;
	@MockBean
	private GameItemFactory gameItemFactory;
	
	private List<GameItem> expected;
	
	@BeforeEach
	public void setup() {
		expected = new ArrayList<GameItem>();
		expected.add(new GameItem("label", EGameValue.ROCK));
		expected.add(new GameItem("label", EGameValue.PAPER));
		expected.add(new GameItem("label", EGameValue.SCISSORS));
		Mockito.when(gameItemFactory.getGameItems()).thenReturn(expected);
	}
	
	@Test
	public void getAllItemsTest() {
		assertEquals(expected, gameService.getAllItems());
	}
	
	@Test
	public void getItemTest() throws GameItemNotFoundException {
		GameItem item = gameService.getItem(EGameValue.PAPER.name());
		assertNotNull(item);
		assertEquals(item.getValue(), EGameValue.PAPER);
		
		item = gameService.getItem(EGameValue.SCISSORS.name());
		assertNotNull(item);
		assertEquals(item.getValue(), EGameValue.SCISSORS);
		
		item = gameService.getItem(EGameValue.ROCK.name());
		assertNotNull(item);
		assertEquals(item.getValue(), EGameValue.ROCK);
	}
	
	@Test
	public void getItemTest_ThrowException() {
		assertThrows(GameItemNotFoundException.class, () -> { gameService.getItem(null);});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.getItem("Invalid");});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.getItem("");});
	}
	
	@Test
	public void playRoundTest() throws GameItemNotFoundException {
		
		GameItem item1 = new GameItem(EGameValue.ROCK.name(), EGameValue.ROCK);
		GameItem item2 = new GameItem(EGameValue.PAPER.name(), EGameValue.PAPER);
		
		RoundResult expected = new RoundResult();
		expected.setPlayer1Choice(item1.getLabel());
		expected.setPlayer2Choice(item2.getLabel());
		expected.setResult(RandomString.make());
		
		Mockito.when(this.game.playRound(item1, item2)).thenReturn(expected);
		
		RoundResult result = gameService.playRound(item1.getValue().name(), item2.getValue().name());
		assertNotNull(result);
		assertEquals(expected.getPlayer1Choice(), result.getPlayer1Choice());
		assertEquals(expected.getPlayer2Choice(), result.getPlayer2Choice());
		assertEquals(expected.getResult(), result.getResult());
		
	}
	
	@Test
	public void playRound_ThrowGameItemotFound() {
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound(null, null);});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound("", EGameValue.SCISSORS.name());});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound(EGameValue.SCISSORS.name(), "");});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound("", "");});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound("Invalid", EGameValue.PAPER.name());});
		assertThrows(GameItemNotFoundException.class, () -> { gameService.playRound(EGameValue.PAPER.name(), "Invalid");});
	}
	
	@Test
	public void getGameResultTest() {
		Game results = gameService.getTotalResults();
		assertNotNull(results);
		assertEquals(results.getTotalRoundsPlayed(), 0);
		assertEquals(results.getTotalDraws(), 0);
		assertEquals(results.getTotalWinsPlayer1(), 0);
		assertEquals(results.getTotalWinsPlayer2(), 0);
	}
}
