package com.dramet.rockpaperscissorsjavareact.model;

import java.io.Serializable;

/**
 * Class that encapsulates the item available for the game.
 * @author dramet
 *
 */
public class GameItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String label;
	private EGameValue value;
	
	public GameItem(String label, EGameValue value) {
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public EGameValue getValue() {
		return this.value;
	}
	
	/**
	 * Determines if the unique value is equals to the object as parameter
	 * @param other
	 * @return
	 */
	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		}
		if(!(other instanceof GameItem)) {
			return false;
		}
		GameItem gameItem = (GameItem)other;
		return gameItem != null && this.value.equals(gameItem.value);
	}
}
