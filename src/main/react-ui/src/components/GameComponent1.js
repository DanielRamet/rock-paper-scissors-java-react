import React, { Component } from 'react';
import GameService from '../services/GameService';
import BootstrapTable from 'react-bootstrap-table-next';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

class GameComponent1 extends Component {

    constructor(props){
        super(props);
        this.state = {
            items: [],
            totalRounds: 0,
            roundList: [],
            gameId: this.props.gameId,
            columns: [{
                dataField: 'id',
                text: 'Round',
                headerStyle: (colum, colIndex) => {
                    return { width: '10%', textAlign: 'center' };
                  }
              }, {
                dataField: 'player1Choice',
                text: 'Player 1',
                headerStyle: (colum, colIndex) => {
                    return { width: '30%', textAlign: 'center' };
                  }
              }, {
                dataField: 'player2Choice',
                text: 'Player 2',
                headerStyle: (colum, colIndex) => {
                    return { width: '30%', textAlign: 'center' };
                  }                
              }, {
                dataField: 'result',
                text: 'Result',
                headerStyle: (colum, colIndex) => {
                    return { width: '30%', textAlign: 'center' };
                  }
              }]
        };
        this.playRound = this.playRound.bind(this);
        this.resetGame = this.resetGame.bind(this);
    }

    componentDidMount(){
        GameService.getAllItems().then(data => {
            this.setState({items: data})
        });

    }

    playRound(){
        this.setState({totalRounds: this.state.totalRounds+1});
        var randomNumber = Math.floor((Math.random() * 3));
        GameService.playRoundPost(this.state.items[randomNumber].value, this.state.items[0].value).then(
            (data) => {this.setState({roundList: this.state.roundList.concat(
                { 
                    id: this.state.roundList.length+1 , 
                    player1Choice: data.player1Choice,
                    player2Choice: data.player2Choice,
                    result: data.result 
                }
                )});}, 
            (error) => {console.log(error);}
        )
    }

    resetGame(){
        this.setState({totalRounds: 0});
        this.setState({roundList: []});
    }

    render(){
        return(
            <div id="content" style={{ marginBottom: 10 }}>
                <h3> GAME (first part): </h3>
                <button id="play-round" onClick={this.playRound}>Play Round</button>
                <button id="reset" onClick={this.resetGame}>Reset Game</button>
                <p><b>TOTAL ROUNDS PLAYED:</b> {this.state.totalRounds}</p>                
                <BootstrapTable striped keyField='id' data={ this.state.roundList } columns={ this.state.columns } />
            </div>
        )
    }
}

export default GameComponent1