import axios from 'axios'

const GAME_REST_API_URL = 'http://localhost:8080/api/game';

class GameService{

    getAllItems(){
        return axios.get(GAME_REST_API_URL + "/all-items");
    }

    getResume(){
        return axios.get(GAME_REST_API_URL + "/resume");
    }

    playRoundPost(player1, player2){
        const request = {
            player1Choice: player1, 
            player2Choice: player2
        };
        return axios.post(GAME_REST_API_URL + "/play-round", request);
    }
}

export default new GameService();