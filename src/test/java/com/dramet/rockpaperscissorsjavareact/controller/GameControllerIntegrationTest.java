package com.dramet.rockpaperscissorsjavareact.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dramet.rockpaperscissorsjavareact.RockPaperScissorsJavaReactApplication;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;
import com.dramet.rockpaperscissorsjavareact.model.request.RoundRequest;

@SpringBootTest(classes = RockPaperScissorsJavaReactApplication.class, 
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getItemsTest() {
		ResponseEntity<GameItem[]> response = 
				restTemplate.getForEntity("/api/game/all-items", GameItem[].class);
			
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().length, 3);
		List<Object> result = Arrays.asList(response.getBody());
		for (EGameValue gameValue : EGameValue.values()) {
			assertTrue(result.stream().filter(gameItem -> gameValue.equals(((GameItem)gameItem).getValue())).findFirst().isPresent());
		}
	}
	
	@Test
	public void postPlayRound_GetResume_Test() throws JSONException {
	    RoundRequest request = new RoundRequest();
	    request.setPlayer1Choice(EGameValue.PAPER.name());
	    request.setPlayer2Choice(EGameValue.ROCK.name());
	    
	    ResponseEntity<RoundResult> response = restTemplate.postForEntity("/api/game/round", request, RoundResult.class);
	    assertNotNull(response);
	    assertNotNull(response.getBody());
	    assertEquals(response.getBody().getPlayer1Choice(), "Paper");
	    assertEquals(response.getBody().getPlayer2Choice(), "Rock");
	    assertEquals(response.getBody().getResult(), "Player 1 Wins");
	    
	    ResponseEntity<RoundResult> roundResult = restTemplate.postForEntity("/api/game/round", request, RoundResult.class);
	    assertNotNull(roundResult);
	    assertEquals(roundResult.getStatusCode(), HttpStatus.OK);
	    
	    request = new RoundRequest();
	    request.setPlayer1Choice(EGameValue.ROCK.name());
	    request.setPlayer2Choice(EGameValue.ROCK.name());
	    roundResult = restTemplate.postForEntity("/api/game/round", request, RoundResult.class);
	    assertNotNull(roundResult);
	    assertEquals(roundResult.getStatusCode(), HttpStatus.OK);
	    
	    ResponseEntity<Game> responseResume = 
				restTemplate.getForEntity("/api/game/resume", Game.class);
			
		assertNotNull(responseResume);
		assertNotNull(responseResume.getBody());
		assertEquals(responseResume.getBody().getTotalWinsPlayer1(), 2);
		assertEquals(responseResume.getBody().getTotalWinsPlayer2(), 0);
		assertEquals(responseResume.getBody().getTotalDraws(), 1);
		assertEquals(responseResume.getBody().getTotalRoundsPlayed(), 3);
	}
	
	@Test
	public void postPlayRoundTest_Exception() {
		RoundRequest request = new RoundRequest();
	    request.setPlayer1Choice("Invalid");
	    request.setPlayer2Choice(EGameValue.ROCK.name());
	    
	    ResponseEntity<RoundResult> response = restTemplate.postForEntity("/api/game/round", request, RoundResult.class);
	    assertNotNull(response);
	    assertNotNull(response.getBody());
	    assertNotNull(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	    
	    request.setPlayer2Choice("Invalid");
	    request.setPlayer1Choice(EGameValue.ROCK.name());
	    
	    response = restTemplate.postForEntity("/api/game/round", request, RoundResult.class);
	    assertNotNull(response);
	    assertNotNull(response.getBody());
	    assertNotNull(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
	}
}
