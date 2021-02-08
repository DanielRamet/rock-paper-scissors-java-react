package com.dramet.rockpaperscissorsjavareact.model;

import java.io.Serializable;

/**
 * Class that handle a Game of two players and results of the play
 * @author dramet
 *
 */
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DRAW_LABEL = "It is a Draw";
	private static final String WIN_SUFFIX = " Wins";
	
	//private Long version;
	
	private int totalWinsPlayer1;
	private int totalWinsPlayer2;
	private int totalDraws;
	
	private Player player1;
	private Player player2;
	
	public Game() {
		this.totalWinsPlayer1 = 0;
		this.totalWinsPlayer2 = 0;
		this.totalDraws = 0;
		
		this.player1 = new Player(Player.DEFAULT_PLAYER1_NAME);
		this.player2 = new Player(Player.DEFAULT_PLAYER2_NAME);
	}
	
	public int getTotalWinsPlayer1() {
		return totalWinsPlayer1;
	}

	public int getTotalWinsPlayer2() {
		return totalWinsPlayer2;
	}

	public int getTotalDraws() {
		return totalDraws;
	}

	public int getTotalRoundsPlayed() {
		return this.totalWinsPlayer1 + this.totalWinsPlayer2 + this.totalDraws;
	}
	
	protected Player getPlayer1() {
		return player1;
	}

	protected Player getPlayer2() {
		return player2;
	}
	
	/**
	 * Obtains the result of a round played between the two players giving a move of each one
	 * @param movePlayer1 Move that the player 1 will play
	 * @param movePlayer2 Move that the player 2 will play
	 * @return RoundResult object
	 */
	public RoundResult playRound(GameItem movePlayer1, GameItem movePlayer2) {
		this.player1.setMove(movePlayer1);
		this.player2.setMove(movePlayer2);
		
		RoundResult roundResult = new RoundResult();
		roundResult.setPlayer1Choice(movePlayer1 != null ? movePlayer1.getLabel() : "");
		roundResult.setPlayer2Choice(movePlayer2!= null ? movePlayer2.getLabel() : "");
		
		EPlayResult result = this.player1.playAgainst(this.player2);
		if(EPlayResult.DRAW.equals(result)) {
			roundResult.setResult(DRAW_LABEL);
			this.totalDraws++;
		}else if (EPlayResult.WIN.equals(result)) {
			roundResult.setResult(this.player1.getName() + WIN_SUFFIX);
			this.totalWinsPlayer1++;
		}else {
			roundResult.setResult(this.player2.getName() + WIN_SUFFIX);
			this.totalWinsPlayer2++;
		}
		
		return roundResult;
	}
}
