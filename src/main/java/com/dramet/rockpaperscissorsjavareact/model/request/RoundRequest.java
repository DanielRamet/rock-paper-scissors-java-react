package com.dramet.rockpaperscissorsjavareact.model.request;

import java.io.Serializable;

/**
 * Class to encapsulate the request to play a round.
 * @author dramet
 *
 */
public class RoundRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String player1Choice;
	private String player2Choice;
	
	public RoundRequest() {}

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

}
