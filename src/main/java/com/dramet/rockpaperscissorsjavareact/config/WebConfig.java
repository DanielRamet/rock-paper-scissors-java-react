package com.dramet.rockpaperscissorsjavareact.config;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.dramet.rockpaperscissorsjavareact.model.Game;

@Configuration
public class WebConfig {

	//HACK: to add identifier ETag to the header of requests/responses
	//@Bean
	//public ShallowEtagHeaderFilter shallowEtaHeaderFilter() {
	//	return new ShallowEtagHeaderFilter();
	//}

	@Bean
	public Game getGame() {
		return new Game();
	}
	
	@Bean
	public ReentrantLock getLock() {
		return new ReentrantLock(true);
	}
}
