import React from 'react';
import GameService from '../services/GameService';

class GameComponent1 extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            items: [],
            totalRounds: 0,
            roundList: [],
            gameId: this.props.gameId
        };
        this.playRound = this.playRound.bind(this);
        this.resetGame = this.resetGame.bind(this);
    }

    componentDidMount(){
        GameService.getAllItems().then(response => {
            this.setState({items: response.data})
        });

    }

    playRound(){
        this.setState({totalRounds: this.state.totalRounds+1});
        var randomNumber = Math.floor((Math.random() * 3));
        GameService.playRoundPost(this.state.items[randomNumber].value, this.state.items[0].value).then(
            (response) => {this.setState({roundList: this.state.roundList.concat(response.data)});}, 
            (error) => {console.log(error);}
        )
    }

    resetGame(){
        this.setState({totalRounds: 0});
        this.setState({roundList: []});
    }

    render(){
        return(
            <div id="content">
                <h3> GAME (first part): </h3>
                <button onClick={this.playRound}>Play Round</button>
                <br/>
                <button onClick={this.resetGame}>Reset Game</button>
                <p><b>TOTAL ROUNDS PLAYED:</b> {this.state.totalRounds} </p>
                <table>
                    <thead>
                        <tr>
                        <th>Round No.</th>
                        <th>Player 1</th>
                        <th>Player 2</th>
                        <th>Result</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.roundList.map(
                            (round, index) => 
                            <React.Fragment key={index}> 
                            <tr>
                                <td>{index + 1}</td>
                                <td>{round.player1Choice}</td>
                                <td>{round.player2Choice}</td>
                                <td>{round.result}</td>
                            </tr>
                            </React.Fragment>
                        )
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default GameComponent1