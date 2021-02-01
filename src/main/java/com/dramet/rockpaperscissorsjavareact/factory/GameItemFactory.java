package com.dramet.rockpaperscissorsjavareact.factory;

import java.util.ArrayList;
import java.util.List;

import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;

public class GameItemFactory {

/**
 * Class to provide a singleton list of all possible choices of the game
 */
private static List<GameItem> items;
	
	public static List<GameItem> getGameItems() {
		if(items == null) {
			items = new ArrayList<GameItem>(3);
			items.add(new GameItem("Rock", EGameValue.ROCK));
			items.add(new GameItem("Paper", EGameValue.PAPER));
			items.add(new GameItem("Scissors", EGameValue.SCISSORS));
		}
		
		return items;
	}

}
