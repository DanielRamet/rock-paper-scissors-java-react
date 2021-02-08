package com.dramet.rockpaperscissorsjavareact.service.impl;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);
	
	@Autowired
	private Game game;

	@Autowired
	private ReentrantLock lock;
	
	@Autowired
	private GameItemFactory gameFactory;
	
	@Override
	public List<GameItem> getAllItems() {
		return gameFactory.getGameItems();
	}

	@Override
	public GameItem getItem(String value) throws GameItemNotFoundException {
		List<GameItem> items = this.getAllItems();
		GameItem result = null;
		if(items != null) {
			try {
				result = items.stream().filter(item -> item.getValue().equals(EGameValue.valueOf(value))).findFirst().orElse(null);
			}catch(Exception e) {
				throw new GameItemNotFoundException(value, e);
			}
		}
		if(result == null) {
			throw new GameItemNotFoundException(value);
		}
		
		return result;
	}

	@Override
	public RoundResult playRound(String player1Choice, String player2Choice) throws GameItemNotFoundException {
		RoundResult result = null;
		lock.lock();
		LOG.info("Thread - " + Thread.currentThread().getName() + " acquired the lock");
        try {
        	 //LOG.info("Thread - " + Thread.currentThread().getName() + " processing");
            // Critical section here
        	GameItem item1 = this.getItem(player1Choice);
      		GameItem item2 = this.getItem(player2Choice);
     		result = this.game.playRound(item1, item2);
        } catch(GameItemNotFoundException exception) {
        	LOG.info("Thread - " + Thread.currentThread().getName() + " throws exception item not found: " + exception.getValue());
        	throw exception;
        //} catch (Exception exception) {
        //    LOG.error(" Interrupted Exception ", exception);
        } finally {
        	LOG.info("Thread - " + Thread.currentThread().getName() + " releasing...");
            lock.unlock();
            LOG.info("Thread - " + Thread.currentThread().getName() + " released the lock");
        }
		return result;
		
	}

	@Override
	public Game getTotalResults() {
		return game;
	}
	
	/* TRY LOCK would be a bit more sophisticated :?
	private void performTryLock() throws InterruptedException{
	    //...
	    boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
	    
	    if(isLockAcquired) {
	        try {
	            //Critical section here
	        } finally {
	            lock.unlock();
	        }
	    }
	    //...
	}
	*/
}
