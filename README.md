# Code Challenge - Ciklum: Rock, Paper, Scissors (rock-paper-scissors-java-react)

This project is a web app that simulates the game Rock, Paper, Scissors using Java + React. 
The aim consist on two parts.

First Part: A web view where two players will play rounds (button) and view the list of rounds played and the number of those rounds. The player 1 will choose randomly one of the choices and the player 2 will choose always Rock option.

Second Part: Provide a resume of the total games played either it is the first part calculation or any other view (browser). Do not use database to store data, keep in memory

## Installation

To install use the following commands: 

Project root directory: 

```
mvn clea install
```

```
mvn spring-boot:run

```

React UI:
Go to {project-root}/src/main/react-ui

```
npm start
```

## Test execution


## Analysis & Operations

The following domain model diagram reproduces the requirements given:

{project-directory}/src/main/resources/domain.jpg

###The API operations are the following (for 'dev' purposes, using http://localhost:8080):

#####GET http://localhost:8080/api/game/all-items. 
Obtain all Items available (i.e: Rock, Paper, Siccssors) elegible by a player.

#####POST http://localhost:8080/api/game/play-round/player1/{player1Choice}/player2/{player2Choice}. 
If the choices are correct (belong to items available), then updates the Game Results (total rounds played) and returns the result of the round played (Choices and which player wins, or draw).

#####GET http://localhost:8080/api/game/resume. 
Obtain the resume of the games played during the life-cycle of the serve side (Total wins of player 1, Total wins of player 2, Total draws, Total games played).

