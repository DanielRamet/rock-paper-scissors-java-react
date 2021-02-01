package com.dramet.rockpaperscissorsjavareact.model;

import java.io.Serializable;

/**
 * Class to give the readable result of a Round played in the game.
 * @author dramet
 *
 */
public class RoundResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String player1Choice;
	private String player2Choice;
	private String result;
	
	public RoundResult() {
		this.player1Choice = null;
		this.player2Choice = null;
		this.result = null;
	}
	
	public String getPlayer1Choice() {
		return player1Choice;
	}
	
	public void setPlayer1Choice(String player1Choice) {
		this.player1Choice = player1Choice;
	}
	
	public String getPlayer2Choice() {
		return player2Choice;
	}
	
	public void setPlayer2Choice(String player2Choice) {
		this.player2Choice = player2Choice;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
}
