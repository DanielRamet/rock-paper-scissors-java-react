import React from 'react';
import GameService from '../services/GameService'

class GameComponent2 extends React.Component {
    interval;
    constructor(props){
        super(props);
        this.state = {
            gameId: this.props.gameId,
            totalRoundsPlayed: 0,
            totalWinsPlayer1: 0,
            totalWinsPlayer2: 0,
            totalDraws: 0
        };
        this.getData = this.getData.bind(this);
    }

    componentDidMount() {
        this.getData();
        this.interval = setInterval(() => 
            this.getData(), 500);
    }
    componentWillUnmount() {
        clearInterval(this.interval);
    }

    getData() {
        GameService.getResume().then(response => 
            this.setState({
                totalDraws: response.data.totalDraws, 
                totalWinsPlayer1: response.data.totalWinsPlayer1,
                totalWinsPlayer2: response.data.totalWinsPlayer2, 
                totalRoundsPlayed: response.data.totalRoundsPlayed}));
    }
    render(){
        return(
            <div id="sidebar">
                <h3>TOTAL GAMES RESUME: (second part)</h3>
                <p>Total Wins Player 1: {this.state.totalWinsPlayer1}</p>
                <p>Total Wins Player 2: {this.state.totalWinsPlayer2}</p>
                <p>Total Draws: {this.state.totalDraws}</p>
                <p><b>Total Rounds Played: {this.state.totalRoundsPlayed}</b></p>
            </div>
            
        )
    }
}

export default GameComponent2