package com.dramet.rockpaperscissorsjavareact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dramet.rockpaperscissorsjavareact.exception.GameItemNotFoundException;
import com.dramet.rockpaperscissorsjavareact.model.Game;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;
import com.dramet.rockpaperscissorsjavareact.model.RoundResult;
import com.dramet.rockpaperscissorsjavareact.model.request.RoundRequest;
import com.dramet.rockpaperscissorsjavareact.service.GameService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/all-items")
	public List<GameItem> getAllItems(){
		return gameService.getAllItems();
	}
	
	@PostMapping(value = "/round", consumes = "application/json", produces = "application/json")
	public RoundResult playRound(@RequestBody RoundRequest request) 
			throws GameItemNotFoundException {
		return gameService.playRound(request.getPlayer1Choice(), request.getPlayer2Choice());
	}
	
	@GetMapping("/resume")
	public Game getResume() {
		return this.gameService.getTotalResults();
	}
}
