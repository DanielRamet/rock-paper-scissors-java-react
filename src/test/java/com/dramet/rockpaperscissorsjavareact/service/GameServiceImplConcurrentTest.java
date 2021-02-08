package com.dramet.rockpaperscissorsjavareact.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;

@SpringBootTest
public class GameServiceImplConcurrentTest {

	@Autowired
	private GameService gameService;
	
	@Test
	public void testCounterWithConcurrency() throws InterruptedException {
	    int numberOfThreads = 10;
	    ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
	    CountDownLatch latch = new CountDownLatch(numberOfThreads);
	    for (int i = 0; i < numberOfThreads; i++) {
	        service.execute(() -> {
	        	try {
					this.gameService.playRound(EGameValue.ROCK.name(), EGameValue.SCISSORS.name());					
				} catch (GameItemNotFoundException e) {
					e.printStackTrace();
					assertTrue(false, "Game item not found");
				} 
	            latch.countDown();
	        });
	    }
	    latch.await();
	    assertEquals(numberOfThreads, this.gameService.getTotalResults().getTotalRoundsPlayed());
	}
}
