package com.dramet.rockpaperscissorsjavareact.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.factory.GameItemFactory;
import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;
import com.dramet.rockpaperscissorsjavareact.service.GameService;

/**
 * Service class to implement methos defined into the interface related
 * @author dramet
 *
 */
@Service
public class GameServiceImpl implements GameService {

	private Game game;
	
	public GameServiceImpl() {
		game = new Game();
	}

	@Override
	public List<GameItem> getAllItems() {
		return GameItemFactory.getGameItems();
	}

	@Override
	public GameItem getItem(String value) throws GameItemNotFoundException {
		List<GameItem> items = this.getAllItems();
		Optional<GameItem> result = null;
		if(items != null) {
			try {
				result = items.stream().filter(item -> item.getValue().equals(EGameValue.valueOf(value))).findFirst();
			}catch(Exception e) {
				throw new GameItemNotFoundException(value, e);
			}
		}
		if(result == null || !result.isPresent()) {
			throw new GameItemNotFoundException(value);
		}
		
		return result.get();
	}

	@Override
	public RoundResult playRound(GameItem player1, GameItem player2) {
		return game.playRound(player1, player2);
	}

	@Override
	public Game getTotalResults() {
		return game;
	}

}
