package com.dramet.rockpaperscissorsjavareact.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dramet.rockpaperscissorsjavareact.model.EGameValue;
import com.dramet.rockpaperscissorsjavareact.model.GameItem;

@SpringBootTest
public class GameItemFactoryTest {

	private GameItemFactory victim;
	
	@BeforeEach
	public void setup() {
		victim = new GameItemFactory();
	}
	
	@Test
	public void createGameItemsTest() {
		List<GameItem> results = victim.getGameItems();
		assertNotNull(results);
		assertEquals(results.size(), 3);
		for (EGameValue eGameVal : EGameValue.values()) {
			assertTrue(results.stream().filter(item -> item.getValue().equals(eGameVal)).findAny().isPresent());
		}
	}
}
