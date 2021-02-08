package com.dramet.rockpaperscissorsjavareact.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GameValueTest {

	@Test
	public void gameValueLosesToTest() {
		assertTrue(EGameValue.ROCK.losesTo(EGameValue.PAPER));
		assertTrue(EGameValue.PAPER.losesTo(EGameValue.SCISSORS));
		assertTrue(EGameValue.SCISSORS.losesTo(EGameValue.ROCK));
		
		//Corner Case when two values are the same
		assertFalse(EGameValue.ROCK.losesTo(EGameValue.ROCK));
		assertFalse(EGameValue.PAPER.losesTo(EGameValue.PAPER));
		assertFalse(EGameValue.SCISSORS.losesTo(EGameValue.SCISSORS));
	}
}
