package com.dramet.rockpaperscissorsjavareact.service;

import java.util.List;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;

/**
 * Service to define methods for the game purposes.
 * @author dramet
 *
 */
public interface GameService {
	
	/**
	 * Obtains all items for the game
	 * @return List of GameItem Object
	 */
	List<GameItem> getAllItems();
	
	/**
	 * Giving a value, obtains the Game item accordingly
	 * @param value String unique value for the game according to rules (ROCK, PAPER, SCISSORS)
	 * @return GameItem object
	 * @throws GameItemNotFoundException in case the value is not correct with the rules of the game
	 */
	GameItem getItem(String value) throws GameItemNotFoundException;
	
	/**
	 * Returns a Play Result giving to players move
	 * @param player1Choice Player 1 move
	 * @param player2Choice Player 2 move
	 * @return Play Round Result with the moves and the label result of the play round
	 * @exception GameItemNotFoundException if any of the choices does not exist ({@link EGameValue}
	 */
	RoundResult playRound(String player1Choice, String player2Choice) throws GameItemNotFoundException
	;
	
	/**
	 * Obtain total results of games played
	 * @return Game instance
	 */
	Game getTotalResults();
}
