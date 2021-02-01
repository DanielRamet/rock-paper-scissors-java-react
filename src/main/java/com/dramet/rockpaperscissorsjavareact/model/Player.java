package com.dramet.rockpaperscissorsjavareact.model;

import java.io.Serializable;

/**
 * Class that represents a player of the game 
 * @author dramet
 *
 */
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_PLAYER1_NAME = "Player 1";
	public static final String DEFAULT_PLAYER2_NAME = "Player 2";
	
	private String name;
	private GameItem move;
	
	public Player(String name) {
		this.name = name;
		this.move = null;
	}

	public String getName() {
		return name;
	}

	/**
	 * Obtains if this player wins, lose or draw against the player passes as parameter
	 * @param player The player that play against this.
	 * @return Enumerated value of the result
	 */
	public EPlayResult playAgainst (Player player) {
		if(this.move == null && (player == null || player.getMove() == null)) {
			return EPlayResult.DRAW;
		}
		if(this.move == null) {
			return EPlayResult.LOSE;
		}
		
		if(player != null) {
			if(this.move.equals(player.getMove())) {
				return EPlayResult.DRAW;
			}else if (player.getMove() != null && this.move.getValue().losesTo(player.getMove().getValue())) {
				return EPlayResult.LOSE;
			}
		}
		
		return EPlayResult.WIN;
	}

	public GameItem getMove() {
		return move;
	}

	public void setMove(GameItem move) {
		this.move = move;
	}
}
