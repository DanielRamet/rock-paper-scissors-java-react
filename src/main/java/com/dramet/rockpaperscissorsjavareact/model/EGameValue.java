package com.dramet.rockpaperscissorsjavareact.model;

import java.util.Arrays;
import java.util.List;

/**
 * Enumerated value to represent the choices of the player´s game.
 * It defines here the relationship (wins/loses) between the different values.
 * @author dramet
 *
 */
public enum EGameValue {
	ROCK, PAPER, SCISSORS;
	
	private List<EGameValue> losesTo;
	
	/**
	 * Determine if this enumerated value loses against the one as parameter
	 * @param other value to compare
	 * @return True if loses against 'other'
	 */
	public boolean losesTo(EGameValue other) {
		return losesTo.contains(other);
	}
	
	/**
	 * Definition of the relationhip between values
	 */
	static {
		ROCK.losesTo = Arrays.asList(PAPER);
		PAPER.losesTo = Arrays.asList(SCISSORS);
		SCISSORS.losesTo = Arrays.asList(ROCK);
	}
}
