import React from 'react';
import ReactDOM from 'react-dom';
import { act } from 'react-dom/test-utils';
import GameComponent1 from '../components/GameComponent1';
import GameService from '../services/GameService';

const fakeResponseItems = [{label:"Rock",value:"ROCK"},{label:"Paper",value:"PAPER"},{label:"Scissors",value:"SCISSORS"}];
const fakeResponsePlayRound = {player1Choice: "Paper", player2Choice: "Rock", result: "Player 1 Wins"}; 

let container;

beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  document.body.removeChild(container);
  container = null;
});

it('Game component 1 can render and load', () => {
    act(() => {
      ReactDOM.render(<GameComponent1 />, container);
    });
    
    const buttons = container.querySelectorAll('button');
    expect(buttons.length).toEqual(2);
    expect(buttons[0].id).toBe("play-round");
    expect(buttons[1].id).toBe("reset");

    const label = container.querySelector('p');
    expect(label.textContent).toBe('TOTAL ROUNDS PLAYED: 0');
  });

  describe("Game component Play Round", () => {
    test("Test Play Round all", async () => {
     
       /*
      jest.spyOn(window, "fetch").mockImplementation(() => {
        const fetchResponse = {
          json: () => Promise.resolve(fakeResponseItems)
        };
        return Promise.resolve(fetchResponse);
      });
    */

      const mockGetItems = jest.spyOn(GameService, 'getAllItems');
      mockGetItems.mockResolvedValue(fakeResponseItems);

      const mockPlay = jest.spyOn(GameService, 'playRoundPost');
      mockPlay.mockResolvedValue(fakeResponsePlayRound);

      await act(async () => {
        ReactDOM.render(<GameComponent1 />, container);
      });

      expect(mockGetItems).toHaveBeenCalledTimes(1);

      const label1 = container.querySelector('p');
      expect(label1.textContent).toBe('TOTAL ROUNDS PLAYED: 0');

      const buttons = container.querySelectorAll('button');
      const buttonPlay = buttons[0];
      const buttonReset = buttons[1];
      await act(async() => {
        buttonPlay.dispatchEvent(new MouseEvent("click", { bubbles: true }));
      });

      expect(mockPlay).toHaveBeenCalledTimes(1);

      const label2 = container.querySelector('p');
      expect(label2.textContent).toBe('TOTAL ROUNDS PLAYED: 1');

      await act(async() => {
        buttonReset.dispatchEvent(new MouseEvent("click", { bubbles: true }));
      });

      const label3 = container.querySelector('p');
      expect(label3.textContent).toBe('TOTAL ROUNDS PLAYED: 0');

      //TODO: test row

      mockGetItems.mockRestore();
      mockPlay.mockRestore();

    });
  });
